/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains constants for parameter attributes such as classtype and list type
 * This will be used by a report or chart
 * @author Merrill George Paul (merrill.george@gmail.com)
 */
public final class ParameterConstants {


    public enum Type {

        DATE("d"),

        LIST("l"),

        QUERY("q"),

        BOOLEAN("b"),

        TEXT("s"),

        /**
         *  <p style="margin-top: 0">
         *        These types of parameters will be aware of the logged on user or rpeort 
         *        trigger user. This is particularly used for paremeters with user id or 
         *        partner id as a criteria in the queries or standalone reports
         *      </p>
         */
        USER_AWARE("u");

        private String key;

        private Type(String key){
            this.key = key;
        }

        public String getKey() {
            return key;
        }
         private static final Map<String,Type> map =
                new HashMap<String,Type>();
        static{
            for(Type vt : Type.values()){
                map.put(vt.getKey(),vt);
            }
        }

        public static Type findByKey(String key){
            return map.get(key);
        }

    }

    /**
     * the target class type of a parameter
     */
    public enum ValueClassType {

        
        
        STRING("s"),

        DOUBLE("d"),

        INTEGER("i"),

        LONG("l"),

        BIGDECIMAL("bd"),

        DATE("dt"),

        TIMESTAMP("t"),

        BOOLEAN("b");

        private String key;
        private static final Map<String,ValueClassType> map =
                new HashMap<String,ValueClassType>();

        private ValueClassType(String key){
            this.key  =  key;
        }

        public String getKey() {
            return key;
        }

        static{
            for(ValueClassType vt : ValueClassType.values()){
                map.put(vt.getKey(),vt);
            }
        }

        public static ValueClassType findByKey(String key){
            return map.get(key);
        }


    }

}

