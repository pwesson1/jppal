# Paypal button generator in Java #

If you don't want to manage your catalogue on PayPal, or if you want to implement your own shopping cart mechanism, your application will need the ability to generate payment buttons. This is where jppal can help you. It can generate encrypted buttons that will redirect the user on the PayPal website, while transmitting the information from your application.

## Configuration ##

For each merchant account used in your application, you will need to provide two files:

  * A .properties file with the parameters of the account,
  * A .jks keystore file that contains the PayPal public certificate, as well as the private key/certificate pair the application is using to sign the requests. The certificate must be registered on PayPal.