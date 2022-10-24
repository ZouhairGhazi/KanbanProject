package fr.tse.fise3.utils;

public class Constants {

	// All task statuses with associated id 
	public static final Long TASK_STATUS_ID_TODO = 1L;
	public static final Long TASK_STATUS_ID_DOING = 2L;
	public static final Long TASK_STATUS_ID_TEST = 3L;
	public static final Long TASK_STATUS_ID_DONE = 4L;

	// All task statuses with associated label
	public static final String TASK_STATUS_LABEL_TODO = "TODO";
	public static final String TASK_STATUS_LABEL_DOING = "DOING";
	public static final String TASK_STATUS_LABEL_TEST = "TEST";
	public static final String TASK_STATUS_LABEL_DONE = "DONE";

	// All task types with associated id 
	public static final Long TASK_TYPES_ID_BUG = 1L;
	public static final Long TASK_TYPES_ID_FEATURE= 2L;

	// All task types with associated label
	public static final String TASK_TYPES_LABEL_BUG = "BUG";
	public static final String TASK_TYPES_LABEL_FEATURE = "FEATURE";
	
	// Left or right movement
	public static final String MOVE_LEFT="LEFT";
	public static final String MOVE_RIGHT="RIGHT";
	
}