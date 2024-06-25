# Inventory-Management-System

## config instructions

1. Generating ssl certificate
```shell
keytool -genkeypair -alias inventoryapi -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650
```

2. Saving keystore and db password
```shell
echo -e "KEYSTORE_PASSWORD=your_keystore_password\nDB_PASSWORD=your_db_password" > .env
```
