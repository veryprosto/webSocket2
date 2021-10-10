package ru.veryprost.webSocket2.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GenerateService {
    public List<Integer> getSimpleNumbersList(int amount){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        int chislo = 1;
        while (list.size() < amount) {
            for (int i = 2; i < chislo; i++) {
                if (chislo % i == 0) break;
                if (i == chislo - 1) list.add(chislo);
            }
            chislo++;
        }
        return list;
    }

    public List<Integer> getRandomGeneratorListByAmount(List<Integer> simpleNumberList, int number) {
        List<Integer> newList = new ArrayList<>(simpleNumberList);
        Collections.shuffle(newList);
        newList = newList.subList(0, number);
        return newList;
    }
}
