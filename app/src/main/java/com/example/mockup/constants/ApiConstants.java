package com.example.mockup.constants;
/**This looks not convenient as url data are not public for outside
 * Java can't find file settings.properties where I have placed all those urls and api keys
 * which any consumers could rewrite in production
 *
 * That's really issue as for changing url of some webservice ,
 * you should go to Java class and change it there.
 *
 * In other words , other clients must know Java code to understand how to change
 * this data.*/
public final class ApiConstants {
    //todo How to read settings.properties file in Dagger2 runtime (throws FileNotFoundException)
    public static final String url = "https://s7fsp9p70b.execute-api.eu-west-3.amazonaws.com/latest/";
    public static final String api_key = "Jl4S6XRmam4ZuGhTmY9Uo4Eqsulu3XPUa2pzQ5sh";
    public static final String api_key_name = "x-api-key";
    public static final String content_type = "application/json";
    public static final String content_type_name = "Content-Type";
}