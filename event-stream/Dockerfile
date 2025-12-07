FROM python:3.11-slim
WORKDIR /app
COPY app.py /app/app.py
RUN pip install Flask==2.3.2
ENV FLASK_APP=app.py
EXPOSE 7000
CMD ["python", "app.py"]
