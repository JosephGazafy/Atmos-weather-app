package com.deloitte.elrr.entity.types;

/**
 * Enum representing the possible actions that can be performed on resources
 * for audit logging purposes. This corresponds to the action_type enum
 * in the database.
 */
public enum ActionType {
    CREATE, READ, UPDATE, DELETE, ASSOCIATE, DISASSOCIATE, ADMIN
}
