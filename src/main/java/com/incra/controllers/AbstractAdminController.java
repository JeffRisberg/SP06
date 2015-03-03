package com.incra.controllers;

import java.util.Calendar;
import java.util.Date;

/**
 * The <i>AbstractAdminController</i> provides utility functions to all
 * controllers which are carrying out admin CRUD operations.
 *
 * @author Jeffrey Risberg
 * @since 12/05/13
 */
//@Secured("ROLE_ADMIN")
public abstract class AbstractAdminController {

    protected Date getNow() {
        Calendar now = Calendar.getInstance();
        return now.getTime();
    }
}
