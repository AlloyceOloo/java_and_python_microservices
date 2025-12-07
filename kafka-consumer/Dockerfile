FROM python:3.11-slim
WORKDIR /app
COPY consumer.py /app/consumer.py
RUN pip install kafka-python==2.0.2
ENV PYTHONUNBUFFERED=1
CMD ["python", "consumer.py"]
