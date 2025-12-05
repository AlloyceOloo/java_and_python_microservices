from flask import Flask, jsonify
app = Flask(__name__)

# Simple mock KYC: odd clientId -> FAIL, even or others -> PASS
@app.route('/kyc/status/<client_id>')
def kyc_status(client_id):
	try:
		n = int(client_id)
		if n % 2 == 1:
			return jsonify({"clientId": client_id, "status": "FAIL"})
	except ValueError:
		pass
	return jsonify({"clientId": client_id, "status": "PASS"})

if __name__ == '__main__':
	app.run(host='0.0.0.0', port=5000)