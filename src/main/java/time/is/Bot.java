package time.is;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        long chat_id=update.getMessage().getChatId();
        if (update.getMessage().hasText()){
            String lastName=update.getMessage().getChat().getLastName();
            String firstName=update.getMessage().getChat().getFirstName();
            SendMessage sendMessage=new SendMessage();
            sendMessage.setText("Assalomu alaykum "+firstName + " "+lastName+" 😍😍😍\n Botimizga xush kelibsiz. Bu botdan siz fastfood 🍕🍔🍟🌭🍿 zakaz berishingiz mumkin\n\n"
            +"Botdan foydalanish uchun ro'yxatdan o'ting : 👇👇👇");
            sendMessage.setChatId(update.getMessage().getChatId());

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true).setSelective(true);
            List<KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton contact = new KeyboardButton()
                    .setText("Raqamni yuborish")
                    .setRequestContact(true);
            keyboardRow.add(contact);
            keyboardRowList.add(keyboardRow);
            replyKeyboardMarkup.setKeyboard(keyboardRowList);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);

//

            if (update.getMessage().hasContact()){
                SendMessage sendMessage1=new SendMessage();
                Contact userContact=update.getMessage().getContact();
                String contactInfo="Sizning kontakt ma'lumotlaringiz: "+userContact.getPhoneNumber();
                sendMessage.setChatId(chat_id);
                sendMessage.setText(contactInfo);


            }else{
                SendMessage sendMessage1=new SendMessage();
                sendMessage1.setChatId(chat_id)
                        .setText("bu tel raqam emas !");
                try {
                    execute(sendMessage1);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "@myy123_bot";
    }

    @Override
    public String getBotToken() {
        return "5602624922:AAGyeVVikA1UaBj9s1GekyuXxq48V0IEJfc";
    }

}
