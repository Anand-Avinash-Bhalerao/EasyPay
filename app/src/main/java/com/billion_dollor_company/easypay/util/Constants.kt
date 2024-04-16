package com.billion_dollor_company.easypay.util

import android.provider.ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME
import androidx.datastore.preferences.core.stringPreferencesKey
import com.billion_dollor_company.easypay.R

object Constants {

    const val TAG = "debugging_tag"


    object PayerDetails {
        const val FIRST_NAME = "Anand"
        const val MIDDLE_NAME = "Avinash"
        const val LAST_NAME = "Bhalerao"
        val IMAGE = R.drawable.male2
        const val UPI_ID = "anandbhalerao@oksbi"
        const val ACCOUNT_NO = "SBI1000000"
        const val BANK_NAME = "State Bank of India"
        const val PHONE_NO = "9175799277"

//        const val FIRST_NAME = "Shruti"
//        const val MIDDLE_NAME = "Sanjay"
//        const val LAST_NAME = "Deokar"
//        val IMAGE = R.drawable.female1
//        const val UPI_ID = "shrutideokar@okicici"
//        const val ACCOUNT_NO = "ICICI1000000"
//        const val BANK_NAME = "ICICI"
//        const val PHONE_NO = "9325591558"

//        const val FIRST_NAME = "Rucha"
//        const val MIDDLE_NAME = "Ramesh"
//        const val LAST_NAME = "Jadhav"
//        val IMAGE = R.drawable.female3
//        const val UPI_ID = "ruchajadhav@okaxis"
//        const val ACCOUNT_NO = "AXIS1000000"
//        const val BANK_NAME = "AXIS"
//        const val PHONE_NO = "8805343429"


        const val CARD_NAME = "$FIRST_NAME $LAST_NAME"
        const val FULL_NAME = "$FIRST_NAME $MIDDLE_NAME $LAST_NAME"

    }

    object Keys {
        const val NPCI_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzlJDxZyYybLotiDimedygowDUGf7x6suqbpULbkGk/YtI+q+6yRWPhYnNeSiBrxgR7xev7Afz7a38FvZbXyUlriPqk0trzacWEyEKbJAogTtc/uKdD4tZ/8FI7qEVpP4SCUjbHxhigQzHR7rfYbzjMMRghk+CS4f0WdJ2LuHBxaIOTYFiaLu/JOKxRbFhStUwG6bbuaBzEdyPIWzt9DnotemcTVpmkgblF1W1dQ3uPf2yRCXQf5YEfvQUxYTQ36tKj9yclyLCgn/8o02SiyBRmRQ1mvbV1k4pLibZByfRWh6/xbPEGQFyqRpQT6FI28rWPh6K9Ehn7gXtjjsRgjcwIDAQAB"
    }

    object Server {
        object PspServer {
            //            const val BASE_URL = "http://10.0.2.2:16000/psp/"
            const val BASE_URL = "http://192.168.1.9:16000/psp/"
            const val USER_INFO_URL = "accountInfo"
            const val TRANSACTION_URL = "transaction"
            const val CHECK_BALANCE_URL = "checkBalance"

            fun getBaseURLArray() = listOf("http://", "/psp")
            fun getBaseURL(ipAddress: String) = "http://$ipAddress:16000/psp/"
        }


    }

    object UserInfo {
        const val UPI_ID = "upiID"

        const val ENCRYPTED_PASSWORD = "encryptedPassword"
    }

    object Values {
        const val AMOUNT = "amount"
        const val ENCRYPTED_PASSWORD = "encryptedPassword"

        const val LOADING = "loading"
        const val SUCCESS = "Success"
        const val FAILED = "Failed"
    }


    object PayeeInfo {
        const val FULL_NAME = "payerFullName"
        const val PHONE_NO = "payerPhoneNo"
        const val UPI_ID = "payerUpiID"
        const val IMAGE_ID = "payerImageID"
        const val ACCOUNT_NO = "payerAccountNo"
        const val BANK_NAME = "payerBankName"
    }

    object PayerInfo {
        const val FULL_NAME = "payeeFullName"
        const val PHONE_NO = "payeePhoneNo"
        const val UPI_ID = "payeeUpiID"
        const val IMAGE_ID = "payeeImageID"
        const val ACCOUNT_NO = "payeeAccountNo"
        const val BANK_NAME = "payeeBankName"
    }


    object Transaction {
        const val SUCCESS = "success"
    }

    object Preferences {
        val IP_KEY = stringPreferencesKey("ip_address")
        val IP_FILE_NAME = "ip_file_name"
    }

}