from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route("/ready")
def ready():
    return "ok", 200

@app.route("/fineract/transactions", methods=["POST"])
def transactions():
    data = request.get_json() or {}
    # simple mock behaviour: if amount > 10000 return 402 else return success
    if data.get("amount",0) > 10000:
        return jsonify({"status":"insufficient_funds"}), 402
    return jsonify({"status":"accepted","ref":"MOCK-" + (data.get("idempotencyKey","x"))}), 200

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=7000)
