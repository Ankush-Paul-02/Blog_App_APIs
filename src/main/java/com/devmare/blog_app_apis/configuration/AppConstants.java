package com.devmare.blog_app_apis.configuration;

public class AppConstants {
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";
    public static final String SORT_BY = "id";
    public static final String SORT_DIRECTION = "asc";

    //? TOken validity in ms
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public static final String SECRET_KEY = "0LbTLwaO5m8drW3biEgJsPV/EeK9Hpcfl4d+VbPao+PKfRjsWqOzmRdg4UGrY4l9";

    public static final Integer ADMIN_USER = 501;
    public static final Integer NORMAL_USER = 502;
}
