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
![home](https://github.com/Anand-Avinash-Bhalerao/EasyPay/assets/87852860/07323f24-eefc-4fc3-8ff9-d18b5f3ef16e)

Transaction Screens:
![scanqr](https://github.com/Anand-Avinash-Bhalerao/EasyPay/assets/87852860/90c85ad5-4936-4d31-9fc0-460d0c29390b) 
![amountEnter](https://github.com/Anand-Avinash-Bhalerao/EasyPay/assets/87852860/c4997e1e-121d-40e7-8907-4d7dc77304b6)
![transactionCL](https://github.com/Anand-Avinash-Bhalerao/EasyPay/assets/87852860/0840929c-40cb-43f9-937f-2b4b4fae8840)
![transactionComplete](https://github.com/Anand-Avinash-Bhalerao/EasyPay/assets/87852860/247230b3-c00f-4032-8370-f557c81e9878)




To make this App work:
1. Setup the PSP/Bank Server. Code for it can be found in my repositories.
2. Setup the NPCI Server. Code for it can be found in my repositories.
3. Setup the mySQL databases.

After installing the app:
1. First set the PSP ip address from the home screen. This is ip address of the machine which is running the PSP server.
2. Fetch the public keys of NPCI by clicking on the Fetch keys option on home screen.
3. Can other features successfully now.
