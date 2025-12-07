import os
import time
from kafka import KafkaConsumer

bootstrap = os.environ.get("KAFKA_BOOTSTRAP_SERVERS", "redpanda:9092")
print("Starting consumer, connecting to", bootstrap)

# auto_offset_reset=earliest helps on first-run
consumer = KafkaConsumer(
    bootstrap_servers=bootstrap,
    auto_offset_reset='earliest',
    group_id='local-consumer-group',
    enable_auto_commit=True,
    value_deserializer=lambda m: m.decode('utf-8') if m else None
)

# subscribe to pattern of our topics
consumer.subscribe(pattern=".*events.*")

try:
    for msg in consumer:
        print("Message:", msg.topic, msg.partition, msg.offset, msg.key, msg.value)
except KeyboardInterrupt:
    print("Stopping consumer")
