package com.example.test.gymjunky.Helperi;

import java.io.Serializable;

/**
 * Created by Adil on 11.5.2015.
 */
public interface MyRunnable<T>  extends Serializable
{
    void run(T result);
}
