package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private ArrayList<Tuple> examResults;


    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.examResults = new ArrayList<>(Arrays.asList(exams));
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        JsonObject[] jsonExamPairs = new JsonObject[examResults.size()];

        for (Tuple<String, Integer> exam : examResults) {
            int passed = 3;
            jsonExamPairs[examResults.indexOf(exam)]= new JsonObject(
                    new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
                    new JsonPair("passed", new JsonBoolean(exam.value >= passed))
            );

        }
        jsonObject.add(new JsonPair("exams", new JsonArray(jsonExamPairs)));
        return jsonObject;
    }
}
