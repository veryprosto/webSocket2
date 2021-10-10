package ru.veryprost.webSocket2.controller;

import com.google.gson.Gson;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.veryprost.webSocket2.model.SimpleNumbers;
import ru.veryprost.webSocket2.service.GenerateService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    final
    GenerateService generateService;

    public MainController(GenerateService generateService) {
        this.generateService = generateService;
    }

    @MessageMapping("/start")
    @SendTo("/topic/generates")
    public String generate(SimpleNumbers simpleNumbers) {
        List<List<Integer>> baseList = new ArrayList<>();
        baseList.add(generateService.getSimpleNumbersList(simpleNumbers.getLengthArray()[0]));
        baseList.add(generateService.getSimpleNumbersList(simpleNumbers.getLengthArray()[1]));
        baseList.add(generateService.getSimpleNumbersList(simpleNumbers.getLengthArray()[2]));
        baseList.add(generateService.getSimpleNumbersList(simpleNumbers.getLengthArray()[3]));
        baseList.add(generateService.getSimpleNumbersList(simpleNumbers.getLengthArray()[4]));

        simpleNumbers.setBaseList(baseList);

        List<List<Integer>> resultList = new ArrayList<>();
        resultList.add(generateService.getRandomGeneratorListByAmount(simpleNumbers.getBaseList().get(0), 6));
        resultList.add(generateService.getRandomGeneratorListByAmount(simpleNumbers.getBaseList().get(1), 6));
        resultList.add(generateService.getRandomGeneratorListByAmount(simpleNumbers.getBaseList().get(2), 6));
        resultList.add(generateService.getRandomGeneratorListByAmount(simpleNumbers.getBaseList().get(3), 6));
        resultList.add(generateService.getRandomGeneratorListByAmount(simpleNumbers.getBaseList().get(4), 6));
        simpleNumbers.setResulList(resultList);

        Gson gson = new Gson();
        return gson.toJson(simpleNumbers.getResulList());
    }

    /*@MessageMapping("/autostart")
    public void autoGenerate(SimpleNumbers simpleNumbers) {
        while (true) {
            generate(simpleNumbers);
            System.out.println("===================running===================");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}


/*
Аннотация @MessageMapping гарантирует, что при отправке сообщения по адресу /start будет вызван метод generate().
Полезная нагрузка сообщения привязывается к объекту SimpleNumbers, который передается в generate().
После того, как клиент отправит сообщение, сервер может асинхронно обрабатывать его столько, сколько ему нужно.
Клиент может продолжать работу, не дожидаясь ответа.
Возвращаемое значение транслируется всем подписчикам /topic/generates, как указано в аннотации @SendTo.
 */