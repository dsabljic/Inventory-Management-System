# Inventory-Management-System

## config instructions
### generating ssl certificate

```shell
keytool -genkeypair -alias inventoryapi -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650
```
### setting environment variables

```shell
export KEYSTORE_PASSWORD=yourpassword
```