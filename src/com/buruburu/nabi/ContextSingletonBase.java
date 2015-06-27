package com.buruburu.nabi;

import android.content.Context;

public class ContextSingletonBase<T>{

  @SuppressWarnings("rawtypes")
  private static ContextSingletonBase instance;
  protected Context context;

  protected ContextSingletonBase(){ }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static <T extends ContextSingletonBase> T getInstance(Class<T> clazz) {
      if(instance == null){
        try {
          instance = clazz.newInstance();
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
      return (T) instance;
  }

  public void init(Context context){
    this.context = context;
  }

  @Override
  protected void finalize() throws Throwable {
    try {
      super.finalize();
    } finally {
      this.context = null;
    }
  }
}