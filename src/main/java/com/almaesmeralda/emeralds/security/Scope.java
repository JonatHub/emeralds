package com.almaesmeralda.emeralds.security;

import lombok.Getter;

@Getter
public enum Scope {
    READ_CLIENT_GRANTS("read:client_grants"),
    CREATE_CLIENT_GRANTS("create:client_grants"),
    DELETE_CLIENT_GRANTS("delete:client_grants"),
    UPDATE_CLIENT_GRANTS("update:client_grants"),
    READ_USERS("read:users"),
    UPDATE_USERS("update:users"),
    DELETE_USERS("delete:users"),
    CREATE_USERS("create:users"),
    READ_USERS_APP_METADATA("read:users_app_metadata"),
    UPDATE_USERS_APP_METADATA("update:users_app_metadata"),
    DELETE_USERS_APP_METADATA("delete:users_app_metadata"),
    CREATE_USERS_APP_METADATA("create:users_app_metadata"),
    READ_USER_CUSTOM_BLOCKS("read:user_custom_blocks"),
    CREATE_USER_CUSTOM_BLOCKS("create:user_custom_blocks"),
    DELETE_USER_CUSTOM_BLOCKS("delete:user_custom_blocks"),
    CREATE_USER_TICKETS("create:user_tickets"),
    READ_CLIENTS("read:clients"),
    UPDATE_CLIENTS("update:clients"),
    DELETE_CLIENTS("delete:clients"),
    CREATE_CLIENTS("create:clients");

    private final String value;

    Scope(String value) {
        this.value = value;
    }

}