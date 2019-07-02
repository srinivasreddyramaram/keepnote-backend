package com.stackroute.keepnote.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.ReminderNotCreatedException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.repository.ReminderRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */

@Service
public class ReminderServiceImpl implements ReminderService {

	/*
	 * Autowiring should be implemented for the ReminderRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	private ReminderRepository reminderRepository;
	@Autowired
	public ReminderServiceImpl(ReminderRepository reminderRepository) {
		super();
		this.reminderRepository = reminderRepository;
	}

	/*
	 * This method should be used to save a new reminder.Call the corresponding
	 * method of Respository interface.
	 */
	public Reminder createReminder(Reminder reminder) throws ReminderNotCreatedException {
		Reminder newReminder = reminderRepository.insert(reminder);
		
		if(newReminder == null)
			throw new ReminderNotCreatedException("Reminder couldn't be created");
		return newReminder;
	}

	/*
	 * This method should be used to delete an existing reminder.Call the
	 * corresponding method of Respository interface.
	 */
	public boolean deleteReminder(String reminderId) throws ReminderNotFoundException {
		Reminder fetchedReminder = getReminderById(reminderId);
		boolean flag = false;
		
		if(fetchedReminder==null)
			throw new ReminderNotFoundException("No reminder found");
		else
			{
				reminderRepository.delete(fetchedReminder);
				flag = true;
			}
		return flag;
	}

	/*
	 * This method should be used to update a existing reminder.Call the
	 * corresponding method of Respository interface.
	 */
	public Reminder updateReminder(Reminder reminder, String reminderId) throws ReminderNotFoundException {
		Reminder fetchedReminder = getReminderById(reminderId);
		if(fetchedReminder==null)
			throw new ReminderNotFoundException("No reminder found");
		else
			{
				fetchedReminder.setReminderType(reminder.getReminderId());
				fetchedReminder.setReminderName(reminder.getReminderName());
				fetchedReminder.setReminderDescription(reminder.getReminderDescription());
				fetchedReminder.setReminderCreatedBy(reminder.getReminderCreatedBy());
				reminderRepository.save(fetchedReminder);
			}
		return reminder;
	}

	/*
	 * This method should be used to get a reminder by reminderId.Call the
	 * corresponding method of Respository interface.
	 */
	public Reminder getReminderById(String reminderId) throws ReminderNotFoundException {
		Reminder reminder = null;
		try{
			reminder = reminderRepository.findById(reminderId).get();
		}
		catch(NoSuchElementException e)
		{
			throw new ReminderNotFoundException("No reminder found");
		}
			return reminder;
	}

	/*
	 * This method should be used to get all reminders. Call the corresponding
	 * method of Respository interface.
	 */

	public List<Reminder> getAllReminders() {
		
		return reminderRepository.findAll();
	}
}