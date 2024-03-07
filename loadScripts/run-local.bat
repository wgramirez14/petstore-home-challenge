k6 run -e BASE_URL=http://localhost:8080  pet-crud-test.js --out influxdb=https://%K6_INFLUXDB_USERNAME%:%K6_INFLUXDB_PASSWORD%@us-east-1-1.aws.cloud2.influxdata.com/k6results
pause