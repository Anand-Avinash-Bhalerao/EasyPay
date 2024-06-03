Welcome to EasyPay app!

This was my final year engineering project. It is an NPCI UPI simulator app.
NPCI is National Payments Corporation of India. It handles the payments in India.
UPI is Unified Payments Interface. Using this technology, you can pay by simply scanning QR codes of the payee and entering a password.

Features:

1. Pay a person by scanning a QR code.
2. Pay a person by upiId or phone no. NOT DONE YET. Felt redundant.
3. Check account Balance
4. Register CL to NPCI
5. Fetch NPCI public Keys.

HomeScreen:

![Samsung Galaxy S20+ Screenshot 0](https://github.com/Anand-Avinash-Bhalerao/EasyPay/assets/87852860/9e957ba6-e0fd-43c0-9364-73de4efb3d60)


Transaction Screens:





To make this App work:
1. Setup the PSP/Bank Server. Code for it can be found in my repositories.
2. Setup the NPCI Server. Code for it can be found in my repositories.
3. Setup the mySQL databases.

After installing the app:
1. First set the PSP ip address from the home screen. This is ip address of the machine which is running the PSP server.
2. Fetch the public keys of NPCI by clicking on the Fetch keys option on home screen.
3. Can other features successfully now.
