package com.contentstack.sdk;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * The type Config test case.
 */
public class ConfigTestCase {


    private static Config configInstance;
    private final static String DEFAULT_BRANCH = "master";

    /**
     * Before all.
     */
    @BeforeClass
    public static void beforeAll() {
        configInstance = new Config();
        assertNull(configInstance.getBranch());
        configInstance.setBranch("developer");
    }


    /**
     * Test branch getter.
     */
    @Test
    public void testBranchGetter() {
        assertEquals("developer", configInstance.getBranch());
    }

    /**
     * Test branch setter.
     */
    @Test
    public void testBranchSetter() {
        String updatedBranch = configInstance.getBranch();
        assertEquals("developer", updatedBranch);
    }

    /**
     * Test branch variable.
     */
    @Test
    public void testBranchVariable() {
        String branchVariable = configInstance.branch;
        assertEquals("developer", branchVariable);
    }

    /**
     * Test branch in config.
     *
     * @throws Exception the exception
     */
    @Test
    public void testBranchInConfig() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Stack stack = Contentstack.stack(appContext, "demoStackApiKey",
                "demoDeliveryToken",
                "environment", configInstance);

        String branch = stack.config.branch;
        assertEquals("developer", branch);
    }

    /**
     * Test api branching config entries.
     *
     * @throws Exception the exception
     */
    @Test
    public void testAPIBranchingConfigEntries() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Config config = new Config();
        String DEFAULT_HOST = BuildConfig.BRANCH_HOST;
        config.setHost(DEFAULT_HOST);
        Stack stack = Contentstack.stack(appContext,
                BuildConfig.BRANCH_API_KEY,
                BuildConfig.BRANCH_DELIVERY_TOKEN,
                BuildConfig.BRANCH_ENVIRONMENT, config);
        Query product = stack.contentType("branchtestcase").query();
        product.find(new QueryResultsCallBack() {
            @Override
            public void onCompletion(ResponseType responseType, QueryResult queryresult, Error error) {

                if (error == null) {
                    JSONArray arrayEntry = null;
                    try {
                        arrayEntry = queryresult.receiveJson.getJSONArray("entries");
                        for (int i = 0; i < 1; i++) {
                            JSONObject map = (JSONObject) arrayEntry.get(i);
                            boolean isBranchAvail = map.has("_branches");
                            JSONArray _branches = map.optJSONArray("_branches");
                            TestCase.assertTrue(isBranchAvail);
                            TestCase.assertEquals(DEFAULT_BRANCH, _branches.get(0));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }


}
