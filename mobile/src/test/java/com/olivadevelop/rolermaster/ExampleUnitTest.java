package com.olivadevelop.rolermaster;

import com.olivadevelop.rolermaster.persistence.controllers.Controllers;
import com.olivadevelop.rolermaster.persistence.entities.old.TestEntity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void constructEntityByJson() {
        JSONObject json = null;
        TestEntity entity = null;
        try {
            json = new JSONObject("{'TestEntity':{'key':1234,'name':'Tester result'}}");
            entity = Controllers.getInstance().getTestController().testParse(json, TestEntity.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertNotNull(entity);
    }
}