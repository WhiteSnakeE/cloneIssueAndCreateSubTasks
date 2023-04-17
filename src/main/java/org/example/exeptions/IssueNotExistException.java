package org.example.exeptions;

public class IssueNotExistException extends RuntimeException{

    public IssueNotExistException(Throwable cause){
        super("Issue does not exist! ",cause);
    }
}
