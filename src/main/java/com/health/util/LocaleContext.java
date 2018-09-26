package com.health.util;

import java.util.Locale;

public class LocaleContext {
    public final static ThreadLocal<Locale> CONTEXT = new ThreadLocal<Locale>();
}
