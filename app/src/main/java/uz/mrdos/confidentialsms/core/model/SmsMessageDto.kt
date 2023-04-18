package uz.mrdos.confidentialsms.core.model

data class SmsMessageDto(var senderName: String?, var phoneNumber: String?, var senderPhoto: String?, var messageContent: String?, var isRead: Boolean)