#!/usr/bin/env bash
# minimal wait-for script: usage: wait-for.sh host:port [host:port ...] -- command...
set -e

TIMEOUT=60
WAIT_SLEEP=1

hosts=()
while [[ "$1" != "--" ]]; do
  hosts+=("$1")
  shift
done
shift

start=$(date +%s)
for h in "${hosts[@]}"; do
  host=$(echo "$h" | cut -d: -f1)
  port=$(echo "$h" | cut -d: -f2)
  echo "waiting for $host:$port ..."
  while ! (echo > /dev/tcp/"$host"/"$port") >/dev/null 2>&1; do
    if [ $(( $(date +%s) - start )) -ge $TIMEOUT ]; then
      echo "Timeout waiting for $host:$port"
      exit 1
    fi
    sleep $WAIT_SLEEP
  done
done

exec "$@"
