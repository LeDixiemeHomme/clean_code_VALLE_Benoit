package interfaces;

import exceptions.DueDatePassedException;

import java.time.LocalDateTime;

public interface IDueDate {
    boolean limitDate(LocalDateTime now, LocalDateTime borrowDate) throws DueDatePassedException;

}
