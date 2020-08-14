package com.lvfree.lvfee.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Atula9286 on 8/6/2017.
 */
public class DataPreferenceManager {

    private static DataPreferenceManager _instance;
    /**
     * Holder for Preference database
     */
    public static final String PREFS_NAME = "_pref_app-Tms_";
    public static final String PREF_TIME = "pref_time";
    /**
     * Holder data for item
     */
    // TODO Define here

    private static SharedPreferences settings;

    /**
     * Need to call setPreferences to init SharedPreferences to store data
     *
     * @return instance of GCDataPreferenceManager
     * @author Atula
     */
    public static DataPreferenceManager getInstance() {
        if (_instance == null) {
            _instance = new DataPreferenceManager();

        }
        return _instance;
    }

    /**
     * No need to setting SharedPreferences to store data
     *
     * @return instance of GCDataPreferenceManager
     * @author Atula
     */
    public static DataPreferenceManager getInstance(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (_instance == null) {
            _instance = new DataPreferenceManager();
        }
        return _instance;
    }

    public DataPreferenceManager setPreferences(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (_instance == null) {
            _instance = new DataPreferenceManager();
        }
        return _instance;
    }

    public DataPreferenceManager setPreferences(SharedPreferences sharedPreference) {
        settings = sharedPreference;
        if (_instance == null) {
            _instance = new DataPreferenceManager();
        }
        return _instance;
    }

    public SharedPreferences getPreferences() {
        return settings;
    }

    /**
     * store boolean data
     *
     * @param holderKey  key to holder boolean data
     * @param holderData data
     * @author Atula
     */
    public void writeBooleanData(String holderKey, boolean holderData) {
        try {
            SharedPreferences.Editor ed = settings.edit();
            ed.putBoolean(holderKey, holderData);
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * store long data
     *
     * @param holderKey  key to holder long data
     * @param holderData data
     * @author Atula
     */
    public void writeLongData(String holderKey, long holderData) {
        try {
            SharedPreferences.Editor ed = settings.edit();
            ed.putLong(holderKey, holderData);
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * store string data
     *
     * @param holderKey  key to holder string data
     * @param holderData data
     * @author Atula
     */
    public void writeStringData(String holderKey, String holderData) {
        try {
            SharedPreferences.Editor ed = settings.edit();
            ed.putString(holderKey, holderData);
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * store string data
     *
     * @param key    key to holder string data
     * @param holder data
     */
    public void writeIntData(String key, int holder) {
        try {
            SharedPreferences.Editor ed = settings.edit();
            ed.putInt(key, holder);
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * store string data
     *
     * @param key key to holder string data
     * @return if error occur or default return 0, else return data
     */
    public int getIntData(String key) {
        try {
            return settings.getInt(key, 0);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getIntData(String key, int defaultValues) {
        int value = 0;

        try {
            value = settings.getInt(key, defaultValues);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * get string data from stored hold by key
     *
     * @param holderString key to holder data
     * @return if error occur return null else return data, "" is default
     * @author Atula
     */
    public String getDataStringFromHolder(String holderString) {
        try {
            return settings.getString(holderString, "");
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDataStringFromHolder(String holderString, String defaultVal) {
        try {
            return settings.getString(holderString, defaultVal);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVal;
    }

    /**
     * get boolean data from stored, its hold by key
     *
     * @param holderString key to holder data
     * @return if error occur or default return true, else return data
     * @author Atula
     */
    public boolean getDataBooleanFromHolder(String holderString) {
        try {
            return settings.getBoolean(holderString, false);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean getDataBooleanFromHolder(String holderString, boolean defaultVal) {
        try {
            return settings.getBoolean(holderString, defaultVal);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * get long data from stored
     *
     * @param holderString key to holder data
     * @return default or error occur return -1 else return data
     * @author Atula
     */
    public long getDataLongFromHolder(String holderString) {
        try {
            return settings.getLong(holderString, -1);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * get long data from stored
     *
     * @param holderString key to holder data
     * @param defaultVal   default value
     * @return default or error occur return -1 else return data
     * @author Atula
     */
    public long getDataLongFromHolder(String holderString, long defaultVal) {
        try {
            return settings.getLong(holderString, defaultVal);
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVal;
    }


    /**
     * remove data
     *
     * @param key key to holder data
     * @author Atula
     */
    public void releaseMemoryFromKey(String key) {
        SharedPreferences.Editor ed = settings.edit();
        ed.remove(key);
        ed.commit();
    }

    /**
     * release all data
     *
     * @author Atula
     */
    public void releaseMemory() {
        SharedPreferences.Editor ed = settings.edit();
        ed.clear();
        ed.commit();
    }
}