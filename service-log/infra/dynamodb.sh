aws --endpoint-url=http://localhost:4566 dynamodb create-table \
    --table-name LogRegistry \
    --attribute-definitions AttributeName=traceId,AttributeType=S \
    --key-schema AttributeName=traceId,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5

aws --endpoint-url=http://localhost:4566 dynamodb list-tables