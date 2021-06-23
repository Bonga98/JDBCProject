/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.data;

/**
 *
 * @authorB Dywili 
 *         NM CEBEKHULU 217063616
 *         L Korasi 217061079
 */
public class DataStorageException extends Exception 
{

    /**
     * Creates a new instance of <code>DataStorageException</code> without
     * detail message.
     */
    public DataStorageException() 
    {
        
    }

    /**
     * Constructs an instance of <code>DataStorageException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DataStorageException(String msg) 
    {
        super(msg);
    }
}
