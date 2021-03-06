package ObjectLabEnterpriseSoftware;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import javax.security.auth.login.LoginContext;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;

/* We want to move this into its own class. For making excel documents based on the DefaultTableModel */
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.sun.jndi.ldap.LdapName;

public class UtilController
{
    private static final boolean SUCCESS = true;
    private static final boolean FAILURE = false;

    private static final String SOFTWARE_NAME = "OLI";
    private static final String SOFTWARE_VERSION = "v2.00"; //Should be dynamic
    
    private static final String USER_GUIDE_URL = "https://drive.google.com/file/d/0ByBesmdK0SzlV0Zha3M1Mmp2SW8/view?usp=sharing";

    private static String studentFname;
    private static String studentLname;
    
    public static String getPageName(String pageName)
    {
        return SOFTWARE_NAME + " " + SOFTWARE_VERSION + " - " + pageName;
    }
    
    public static String getStudentFname()
    {
    	return studentFname;
    }
    
    public static String getStudentLname()
    {
    	return studentLname;
    }
    
    public static void setStudentFName(String firstName)
    {
    	studentFname = firstName;
    }
    
    public static void setStudentLName(String lastName)
    {
    	studentLname = lastName;
    }
    

    private static void print(ArrayList<ArrayList<Object>> q)
    {
        for (int i = 0; i < q.size(); i++)
        {
            ArrayList<Object> row = q.get(i);
            for (int j = 0; j < row.size(); j++)
            {
                System.out.print(" " + (String) row.get(j) + " ");
            }
            System.out.println("\nRow 0");
        }
    }

    public static void openAdminHelpPage()
    {
        if (Desktop.isDesktopSupported())
        {
            try
            {
                Desktop.getDesktop().browse(new URI(USER_GUIDE_URL));
            } catch (IOException ex)
            {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (URISyntaxException ex)
            {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String[] arrayListToStringArray(ArrayList<String> toConvert)
    {
        return toConvert.toArray(new String[toConvert.size()]);
    }

    public static ArrayList<String> getListOfAllDevices()
    {
        SQLMethods dbconn = new SQLMethods();
        ResultSet queryResult = dbconn.getAllDeviceNames();
        ArrayList<String> printers = new ArrayList<String>();
        ArrayList<ArrayList<Object>> data = readyOutputForViewPage(queryResult);

        for (ArrayList<Object> data1 : data)
        {
            printers.add(data1.get(0).toString());
        }

        dbconn.closeDBConnection();
        return printers;
    }

    public static ArrayList<String> getListOfCurrentDevices()
    {
        SQLMethods dbconn = new SQLMethods();
        ResultSet queryResult = dbconn.getCurrentDevices();
        ArrayList<String> printers = new ArrayList<String>();
        ArrayList<ArrayList<Object>> data = readyOutputForViewPage(queryResult);

        for (ArrayList<Object> data1 : data)
        {
            printers.add(data1.get(0).toString());
        }

        dbconn.closeDBConnection();
        return printers;
    }

    public static ArrayList<String> getListOfCurrentTrackedDevices()
    {
        SQLMethods dbconn = new SQLMethods();
        ResultSet queryResult = dbconn.getTrackedDevices();
        ArrayList<String> printers = new ArrayList<String>();
        ArrayList<ArrayList<Object>> data = readyOutputForViewPage(queryResult);

        for (ArrayList<Object> data1 : data)
        {
            printers.add(data1.get(0).toString());
        }

        dbconn.closeDBConnection();
        return printers;
    }

    public static String[] getReportColumnHeaders(String printer_name)
    {
        try
        {
            SQLMethods dbconn = new SQLMethods();
            ResultSet queryResult = dbconn.getReport(printer_name);
            /* Must process results found in ResultSet before the connection is closed! */

            ResultSetMetaData rsmd = queryResult.getMetaData();
            String[] headers = new String[rsmd.getColumnCount()];
            //System.out.println(rsmd.getColumnName(5));
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                headers[i - 1] = rsmd.getColumnName(i);
            }

            dbconn.closeDBConnection();
            return headers;
        } catch (SQLException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

    public static String[] getRealReportColumnHeaders(String printer_name)
    {
        try
        {
            SQLMethods dbconn = new SQLMethods();
            ResultSet queryResult = dbconn.getReport(printer_name);
            /* Must process results found in ResultSet before the connection is closed! */

            ResultSetMetaData rsmd = queryResult.getMetaData();
            String[] headers = new String[rsmd.getColumnCount()];
            //System.out.println(rsmd.getColumnName(5));
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                headers[i - 1] = rsmd.getColumnName(i);
            }

            dbconn.closeDBConnection();
            return headers;
        } catch (SQLException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    
    
    
    
    
    public static String[] getStatusJobsHeaders(String status)
    {
        try
        {
            SQLMethods dbconn = new SQLMethods();
            ResultSet queryResult = dbconn.searchJobsStatus(status);
            /* Must process results found in ResultSet before the connection is closed! */

            ResultSetMetaData rsmd = queryResult.getMetaData();
            String[] headers = new String[rsmd.getColumnCount()];
            String[] headername = new String[]{"File Name", "First Name","Last Name","Submission Date","Printer Name","Class Name","Class Section"};
            //System.out.println(rsmd.getColumnName(5));
            for (int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                headers[i - 1] = headername[i-1];
            }

            dbconn.closeDBConnection();
            return headers;
        } catch (SQLException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<ArrayList<Object>> updateReportTableData(String printer_name)
    {
        SQLMethods dbconn = new SQLMethods();
        ResultSet queryResult = dbconn.getReport(printer_name);

        ArrayList<ArrayList<Object>> retval = readyOutputForViewPage(queryResult);

        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();

        return retval;

    }

    public static ArrayList<ArrayList<Object>> updateReportTableData(String column, String value, String printer_name)
    {
        SQLMethods dbconn = new SQLMethods();
        ResultSet queryResult = dbconn.getReport(column, value, printer_name);

        ArrayList<ArrayList<Object>> retval = readyOutputForViewPage(queryResult);

        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();

        return retval;
    }

    private static String hashPassword(String pass)
    {
        try
        {
            MessageDigest sh = MessageDigest.getInstance("SHA-512");
            sh.update(pass.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : sh.digest())
            {
                sb.append(Integer.toHexString(0xff & b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    static void updateAdminPassword(String input)
    {

        String passHash = hashPassword(input);

        SQLMethods dbconn = new SQLMethods();
        dbconn.updatePassword(passHash, "Admin");


        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();

    }

    static boolean checkAdminLogin(String input)
    {

        String passHash = hashPassword(input);
        String passFromDb = null;

        SQLMethods dbconn = new SQLMethods();

        ResultSet queryResult = dbconn.selectPassFromadmin("Admin");

        ArrayList<ArrayList<Object>> retval = readyOutputForViewPage(queryResult);

        try
        {
            queryResult.first();
            passFromDb = queryResult.getString(1);
        } catch (Exception ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();

        if (passFromDb.compareTo(passHash) == 0)
        {
            return true;
        } else
        {
            return false;
        }

    }

    public void exportReportToFile(DefaultTableModel model, String[] header, String printer, char type)
    {

        FileManager fileManager = new FileManager();

        Workbook wb = new HSSFWorkbook();
        //TODO: pick better sheet name
        Sheet sheet = wb.createSheet("new sheet");
        Row row = null;
        Cell cell = null;
        Calendar c = Calendar.getInstance();
        String time = "" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.YEAR);

        for (int i = 0; i < model.getRowCount() + 1; i++)
        {
            row = sheet.createRow(i);
            if (i == 0)
            {
                for (int j = 0; j < header.length; j++)
                {
                    cell = row.createCell(j);
                    cell.setCellValue(header[j]);
                }
            } else
            {
                for (int j = 0; j < model.getColumnCount(); j++)
                {
                    cell = row.createCell(j);
                    cell.setCellValue((String) model.getValueAt(i - 1, j));
                }
            }
        }
        String reportName = "";
        if(type == 'b')
            reportName = printer + " build chart " + time;
        else if (type == 'f')
            reportName = printer + " file tracking " + time;
        boolean didSave = fileManager.saveReport(reportName, wb);

        if (didSave)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Succesfully Exported File");
        } else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Unable To Exported File");
        }

    }

    public static void exportReportsForPrinters()
    {

        ArrayList<String> printers = UtilController.getListOfCurrentDevices();
        FileManager fileManager = new FileManager();

        Workbook wb = new HSSFWorkbook();

        String[] columnHeaders;
        ArrayList<ArrayList<Object>> data;
        Sheet sheet;

        for (int x = 0; x < printers.size(); x++)
        {

            sheet = wb.createSheet(printers.get(x));
            columnHeaders = getReportColumnHeaders(printers.get(x));
            data = updateReportTableData(printers.get(x));
            Row row = null;
            Cell cell = null;
            if (data.size() > 0)
            {
                for (int i = 0; i < data.size() + 1; i++)
                {
                    row = sheet.createRow(i);
                    if (i == 0)
                    {
                        for (int j = 0; j < data.get(i).size(); j++)
                        {
                            cell = row.createCell(j);
                            cell.setCellValue(columnHeaders[j]);
                        }
                    } else
                    {
                        for (int j = 0; j < data.get(i - 1).size(); j++)
                        {
                            cell = row.createCell(j);
                            cell.setCellValue((String) data.get(i - 1).get(j));
                        }
                    }
                }
            }
        }
        boolean didSave = fileManager.saveReport("MasterReport", wb);

        if (didSave)
        {
            JOptionPane.showMessageDialog(new JFrame(), "Succesfully Exported File");
        } else
        {
            JOptionPane.showMessageDialog(new JFrame(), "Unable To Exported File");
        }
    }

    public static boolean checkExtension(String printer, String submittedFileExtension)
    {
		boolean isValid = false;
		SQLMethods dbconn = new SQLMethods();
		
		ResultSet results = dbconn.selectAcceptedFiles(printer);
		
		try 
		{
			String ext;
			while(results.next())
			{
				ext = results.getString(1);
				if(ext.compareTo(submittedFileExtension) == 0)
				{
					// The file matches a valid extension in the DB.
					// Return true.
					System.out.println("The file extension is valid.");
					return true;
				}
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("The file extension is invalid:\n" + e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The file extension is invalid. No match was found.");
    	return isValid;
    }
    
    public static boolean rejectStudentSubmission(String file, String reasonForRejection)
    {
        SQLMethods dbconn = new SQLMethods();
        
        dbconn.updateFileComment(file, reasonForRejection);        
        
        ResultSet results = dbconn.searchID((file));
        try
        {
            String emailadr, emailMessage, primaryKey, fName, lName;
            File locationOfRejectedFiles, rejectionFile;
            FileManager cloudStorageOperations = new FileManager();

            /* Query the DB for our emailadr here */
            if (results.next())
            {
                primaryKey = results.getString("job_id");

                ResultSet queryResultData = dbconn.searchWithJobID(primaryKey);
                if (queryResultData.next())
                {
                    emailadr = queryResultData.getString("email");
                    fName = queryResultData.getString("first_name");
                    lName = queryResultData.getString("last_name");

                } else
                {
                    dbconn.closeDBConnection();
                    return FAILURE;
                }
            } else
            {
                dbconn.closeDBConnection();
                return FAILURE;
            }

            /* Create rejected directory if it does not exist
             if(!cloudStorageOperations.doesFileExist(cloudStorageOperations.getRejected()))
             cloudStorageOperations.create(cloudStorageOperations.getRejected());
             */
            /* Move our rejected file to the rejected files directory */
            locationOfRejectedFiles = new File(cloudStorageOperations.getRejected());
            rejectionFile = new File(cloudStorageOperations.getSubmission() + file);

            if (rejectionFile.exists())
            {
                FileUtils.moveFileToDirectory(rejectionFile, locationOfRejectedFiles, true);
            } else
            {
                dbconn.closeDBConnection();
                return FAILURE;
            }

            /* 
             Delete the job that was rejected from the pendingjobs table. Close socket conn after we do so 
             */
            String newFileLocation = cloudStorageOperations.getRejected() + file;
            dbconn.updateJobFLocation(Integer.parseInt(primaryKey), newFileLocation.replace("\\", "\\\\"));
            dbconn.updateStatus("rejected", Integer.parseInt(primaryKey));
            
            //reasonForRejection = dbconn.getFileComment(file);
            
            dbconn.closeDBConnection();

            emailMessage = "Dear " + fName + " " + lName + ", \n\nAfter analyzing your file submission, "
                    + file + ", we have found the following error: \n\nComment: " + reasonForRejection
                    + "\n\nPlease fix the file and resubmit." + "\n\nThank you,\nObject Lab Staff";
            //Backup email
            //TowsonOli@gmail.com passwordTowson 
            EmailUtils.send(emailadr, "TowsonuObjectLab@gmail.com", "oblabsoftware", emailMessage);
            return true; 
        } catch (SQLException ex)
        {
            System.out.println("Program crashed in reject subm\n" + ex);
        } catch (IOException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbconn.closeDBConnection();
        return FAILURE;
    }

    public static void approveStudentSubmission(String fileName, double volume)
    {
        /* Make the connection to our DB and query for the PK of pendingjobs which is a combination of
         all the fields input in the searchID method call
         */
        SQLMethods dbconn = new SQLMethods();
        ResultSet result = dbconn.searchID((fileName));
        FileManager cloudStorageOperations = new FileManager();

        String ID;

        try
        {
            /* 
             If the row exist, then query for the PK and then use that to update 
             the pendingjobs table fileLocation. -Nick 
             */
            if (result.next())
            {
                ID = result.getString("job_id");
                String printer = result.getString("printer_name");
                String updatedDirectoryLocation = cloudStorageOperations.getDrive() + "\\ObjectLabPrinters\\" + printer + "\\ToPrint\\";
                String currentFileLocation = cloudStorageOperations.getSubmission() + fileName;

                /* This moves the file from the submissions folder to the toPrint folder in folder specified by 
                 *  the printer variable -Nick
                 */
                FileUtils.moveFileToDirectory(new File(currentFileLocation), new File(updatedDirectoryLocation), true);

                /* In order to properly update the file location we need to gurantee there are '\\' seperating
                 *  the dir names.
                 *  If ther are no double backslashes than the character will not be escaped properly, and
                 *  the DB will not contain the correct file location.
                 *   - Nick
                 */
                dbconn.updateJobVolume(Integer.parseInt(ID), volume);

                /* update full file path */
                updatedDirectoryLocation += fileName;
                dbconn.updateJobFLocation(Integer.parseInt(ID), updatedDirectoryLocation.replace("\\", "\\\\"));
                dbconn.changeJobStatus(Integer.parseInt(ID), "approved");
                dbconn.closeDBConnection();
            }
        } catch (SQLException | IOException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void changeStudentBalance(String printer, String id, double stat1, double stat2)
    {
        SQLMethods dbconn = new SQLMethods();
        if(printer.equalsIgnoreCase("Z Printer 250"))
        {
            dbconn.addMaterial(id, -(stat1), "z_corp_plaster");
        }
        else if (printer.equalsIgnoreCase("Objet Desktop 30"))
        {
            dbconn.addMaterial(id, -(stat1), "objet_build");
            dbconn.addMaterial(id, -(stat2), "objet_support");
        } 
        else
        {
            System.out.println("Error");
        }
        dbconn.closeDBConnection();
    }
    
    public static void changeStudentBalanceHistory(String printer, String id, double stat1, double stat2)
    {
    	SQLMethods dbconn = new SQLMethods();
        if(printer.equalsIgnoreCase("Z Printer 250"))
        {
            //dbconn.addMaterial(id, -(stat1), "z_corp_plaster");
        	dbconn.addTransactionHistory(id, "z_corp_plaster", -(stat1));
        }
        else if (printer.equalsIgnoreCase("Objet Desktop 30"))
        {
            //dbconn.addMaterial(id, -(stat1), "objet_build");
            //dbconn.addMaterial(id, -(stat2), "objet_support");
        	dbconn.addTransactionHistory(id, "objet_build", -(stat1));
        	dbconn.addTransactionHistory(id, "objet_support", -(stat2));
        } 
        else
        {
            System.out.println("Error");
        }
        dbconn.closeDBConnection();
    }
    
    /***
     * 
     * @param printer	The printer the student is submitting to
     * @param id		The student's ID
     * @param stat1		The amount of stat1 the student wants to spend
     * @param stat2		The amount of stat2 the student wants to spend
     * @return			True/False: Would this transaction run a negative balance?
     */
    public static boolean checkNegativeBalance(String printer, String id, double stat1, double stat2)
    {
    	boolean retVal = false;
    	SQLMethods dbconn = new SQLMethods();
    	
    	ResultSet queryResult = dbconn.searchStudentBalanceId(id);
    	try {
			while(queryResult.next())
			{
			        String first_name = queryResult.getString(1);
			        String last_name= queryResult.getString(2);
			        String towson_id = queryResult.getString(3);
			        double zcorp = queryResult.getDouble(4);
			        double obbuild = queryResult.getDouble(5);
			        double obsupport  = queryResult.getDouble(6);
			        
			        if(printer.equalsIgnoreCase("Objet Desktop 30"))
			        {
			        	if(obbuild - stat1 < 0)
			        	{
			        		retVal = true;
			        	}
			        	else if(obsupport - stat2 < 0)
			        	{
			        		retVal = true;
			        	}
			        }
			        else if (printer.equalsIgnoreCase("Z Printer 250"))
			        {
			        	if(zcorp - stat1 < 0)
			        	{
			        		retVal = true;
			        	}
			        }
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e);
		}
    	
    	
    	return retVal;
    }
    
    public static void approveStudentSubmission(String fileName, String stat1, String stat2)
    {
        /* Make the connection to our DB and query for the PK of pendingjobs which is a combination of
         all the fields input in the searchID method call
         */
        SQLMethods dbconn = new SQLMethods();
        ResultSet result = dbconn.searchID((fileName));
        FileManager cloudStorageOperations = new FileManager();

        String ID;

        try
        {
            /* 
             If the row exist, then query for the PK and then use that to update 
             the pendingjobs table fileLocation. -Nick 
             */
            if (result.next())
            {
                ID = result.getString("job_id");
                String printer = result.getString("printer_name");
                String updatedDirectoryLocation = cloudStorageOperations.getDrive() + "\\ObjectLabPrinters\\" + printer + "\\ToPrint\\";
                String currentFileLocation = cloudStorageOperations.getSubmission() + fileName;

                /* This moves the file from the submissions folder to the toPrint folder in folder specified by 
                 *  the printer variable -Nick
                 */
                FileUtils.moveFileToDirectory(new File(currentFileLocation), new File(updatedDirectoryLocation), true);

                /* In order to properly update the file location we need to gurantee there are '\\' seperating
                 *  the dir names.
                 *  If ther are no double backslashes than the character will not be escaped properly, and
                 *  the DB will not contain the correct file location.
                 *   - Nick
                 */
                dbconn.updateStats(Integer.parseInt(ID), stat1, stat2);

                /* update full file path */
                updatedDirectoryLocation += fileName;
                dbconn.updateJobFLocation(Integer.parseInt(ID), updatedDirectoryLocation.replace("\\", "\\\\"));
                dbconn.changeJobStatus(Integer.parseInt(ID), "approved");
                dbconn.closeDBConnection();
            }
        } catch (SQLException | IOException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /* This function has the params that make up the primary key in pending currentJob.
     It returns the filePath found in the DB where all the parameters specify the file
     that is associated for that users submission.
     */
    public static File getFilePath(String firstName, String lastName, String fileName, String dateSubmitted)
    {
        SQLMethods dbconn = new SQLMethods();
        File filePath = null;

        ResultSet result = dbconn.searchID((fileName));

        try
        {
            if (result.next())
            {
                filePath = new File(result.getString("file_path"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dbconn.closeDBConnection();
        return filePath;
    }

    /**
     * *
     * @param filepath
     * @return
     *
     * Check if the file exists in the given filepath. If it doesn't, prompt the
     * user to search for the file. If user is unable to find the file, delete
     * it from the database.
     */
    public static boolean checkFileExists(String filepath)
    {
        boolean exists = new FileManager().doesFileExist(filepath);
        //If the files does not exist and the user does not locate it
        if (!exists)
        {
            //TODO: update file location in database
        }
        return true;
    }

    /* This function takes the column names found in the queryResult and inserts them into columnNames */
    private static ArrayList<String> getColumnNames(ResultSet queryResult)
    {
        ArrayList<String> columnNames = new ArrayList<>();

        try
        {
            /* Meta data contains number of columns and there names.
             * This is used to get entire rows of data in order to put into dataVector
             * -Nick
             */
            ResultSetMetaData meta = queryResult.getMetaData();
            int columnCount = meta.getColumnCount();

            for (int column = 1; column <= columnCount; column++)
            {

                columnNames.add(meta.getColumnName(column));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(SQLMethods.class.getName()).log(Level.SEVERE, null, ex);
            return columnNames;
        }

        return columnNames;
    }

    /* This function is called to take the query result "ResultSet" return type form a DB query and re-format it into 
     * general structures that make it easy for the View classes to display / modify.
     * Therefore the View classes would not have to know about the data and column names ect... 
     * -Nick
     */
    private static ArrayList<ArrayList<Object>> readyOutputForViewPage(ResultSet queryResult)
    {
        ArrayList<String> columnNames = getColumnNames(queryResult);
        ArrayList<ArrayList<Object>> retval = new ArrayList<>();

        /* Process data column by column and add that data into dataVector */
        try
        {
            ArrayList<Object> tempRow;

            /* Goes through ResultSet row by row adding the row data into retval as an arraylist of type string */
            while (queryResult.next())
            {
                tempRow = new ArrayList<>();
                for (String columnName : columnNames)
                {
                    tempRow.add((Object) queryResult.getString((String) columnName));
                }

                retval.add(tempRow);
            }
        } catch (SQLException sqle)
        {
            System.out.println(sqle);
            return retval;
        }
        return retval;
    }

    public static ArrayList<ArrayList<Object>> updatePendingTableData(String status)
    {
        SQLMethods dbconn = new SQLMethods();
        ResultSet queryResult = dbconn.searchJobsStatus(status);

        ArrayList<ArrayList<Object>> retval = readyOutputForViewPage(queryResult);

        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();
        return retval;
    }

    public static String[] returnAvailableDevicesStudentSubmissionRequired()
    {
        /*
         Fetch available printers
         */

        SQLMethods dbconn = new SQLMethods();
        ResultSet printersAvailableResult = dbconn.getDeviceNamesCurrentOptionSubmissionOption(true, true);
        ArrayList<ArrayList<Object>> printersAvailableAL = readyOutputForViewPage(printersAvailableResult);
        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();

        /* Convert results to desired format */
        String[] printersAvailble = new String[printersAvailableAL.size()];
        for (int row = 0; row < printersAvailableAL.size(); row++)
        {
            printersAvailble[row] = (String) printersAvailableAL.get(row).get(0);
        }

        return printersAvailble;
    }

    public static String[] returnAvailableClasses()
    {
        /*
         Fetch available classes
         */

        SQLMethods dbconn = new SQLMethods();
        ResultSet classesAvailableResult = dbconn.getClasses(true);
        ArrayList<ArrayList<Object>> classesAvailableAL = readyOutputForViewPage(classesAvailableResult);
        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();

        /*
         Convert results to desired format
         */
        String[] classesAvailble = new String[classesAvailableAL.size()];
        for (int row = 0; row < classesAvailableAL.size(); row++)
        {
            String tempRow = "";
            ArrayList<Object> tmplist;

            tmplist = (ArrayList<Object>) classesAvailableAL.get(row);

            for (int column = 0; column < tmplist.size(); column++)
            {
                tempRow += tmplist.get(column) + " ";
            }

            classesAvailble[row] = tempRow;
        }

        return classesAvailble;
    }

    public static String[] listOfBuilds()
    {
        /*
         Fetch available builds
         */

        SQLMethods dbconn = new SQLMethods();
        ResultSet buildsResult = dbconn.getBuilds();
        ArrayList<ArrayList<Object>> builds = readyOutputForViewPage(buildsResult);
        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();

        /* Convert results to desired format */
        String[] returnBuilds = new String[builds.size()];
        for (int row = 0; row < builds.size(); row++)
        {
            returnBuilds[row] = (String) builds.get(row).get(0);
        }
        return returnBuilds;
    }
    
    //Rajewski
    //done
    
    public static ArrayList<ArrayList<Object>> arrayListOfBuilds(String currentDevice)
    {
        /*
         Fetch available builds
         */

        SQLMethods dbconn = new SQLMethods();
        ResultSet buildsResult = dbconn.getBuildsForRecordsTable(currentDevice);
        ArrayList<ArrayList<Object>> builds = readyOutputForViewPage(buildsResult);
        /* Must process results found in ResultSet before the connection is closed! */
        dbconn.closeDBConnection();

        return builds;
    }
	
    public static void submitStudentFile(String userID, String fileLocation, String fileName, String printerName, int classFK)
    {
        /*
         Establish connection to DB
         */
        SQLMethods dbconn = new SQLMethods();

        try
        {
            /*
             Get new file location for submission location
             */
            FileManager instance = new FileManager();
            String newFileLoc = instance.getSubmission() + fileName;
            newFileLoc = newFileLoc.replace("\\", "\\\\");

            /*
             Now copy the old file to the new file location
             */
            FileUtils.copyFile(new File(fileLocation), new File(newFileLoc));
            dbconn.insertIntoJob(fileName, newFileLoc, classFK, userID, printerName);

        } catch (IOException e)
        {
            javax.swing.JOptionPane.showMessageDialog(new java.awt.Frame(), "IOException! File couldn't be navigated.");
        }
        /*
         Close the DB connection
         */
        dbconn.closeDBConnection();
    }

    public static String getCurrentTimeFromDB()
    {
        /*
         Establish connection to DB
         */
        SQLMethods dbconn = new SQLMethods();

        /*
         Parse result as single string
         */
        String currTime = null;
        ResultSet res = dbconn.getCurrentTime();
        try
        {
            int count = 1;
            while (res.next())
            {
                currTime = res.getString(count);
                count++;
            }
        } catch (SQLException sqlError)
        {
            sqlError.printStackTrace();
        }

        /*
         Append a "_" so that project names can be differentiated from timestamp
         */
        currTime = "_" + currTime;
        dbconn.closeDBConnection();

        return (String) currTime.trim();
    }

    /**
     * Updates view for making a build. This will show files/currentJob (student
     * submissions) that need to be put into a build UNFINISHED ****
     *
     * This method is called in: PrinterBuild.updateView
     *
     * @param printer the printer being viewed
     * @return
     */
    public static ArrayList<ArrayList<Object>> updatePrinterBuildView(String printer)
    {
        SQLMethods dbconn = new SQLMethods();

        ResultSet result = dbconn.searchApprovedJobsNotPrinted(printer);
        ArrayList<ArrayList<Object>> approvedForPrinter = readyOutputForViewPage(result);
        dbconn.closeDBConnection();

        return approvedForPrinter;
    }

    public static void deleteAllFiles(){
        FileManager fm = new FileManager();
        fm.deleteFiles();
    }
    
   public static void archive(String from, String to)
    {
        try
        {
            exportReportsForPrinters();
            String fileName = new String();
            String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
            fileName = "Archive " + date;
            System.out.println((from.replace("\\", "\\\\") + "\\\\") + " : " + (to.replace("\\", "\\\\") + "\\\\" + fileName + ".zip"));
            FileManager.zip((from.replace("\\", "\\\\") + "\\\\"), (to.replace("\\", "\\\\") + "\\\\" + fileName + ".zip"));

            if (FileManager.doesFileExist((to.replace("\\", "\\\\") + "\\\\" + fileName + ".zip")))
            {
                JOptionPane.showMessageDialog(new JFrame(), "Archive Successful");
            } else
            {
                JOptionPane.showMessageDialog(new JFrame(), "Archive Failed");
            }
        } catch (IOException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void clearData()
    {

        SQLMethods dbconn = new SQLMethods();
        dbconn.clearData();
        dbconn.closeDBConnection();

    }

    public static void updateAvailableClasses(ArrayList<String> classData)
    {
        SQLMethods dbconn = new SQLMethods();

        // SET all classes in current's current value to true and all else to false
        dbconn.setAllClassesInvisible();

        for (String currentClass : classData)
        {
            dbconn.updateCurrentClasses(Integer.parseInt(currentClass.split(" ")[0]));
        }

        dbconn.closeDBConnection();
    }

    public static boolean addDevice(Device deviceModel)
    {
        SQLMethods dbconn = new SQLMethods();
        String deviceName = deviceModel.getDeviceName();
        boolean deviceTrack = deviceModel.getTrackSubmission();
        ArrayList<String> fieldNames = deviceModel.getFieldNames();
        ArrayList<String> fileExt = deviceModel.getFileExtensions();

        /* Insert our printer into the printer table. For right now just adding in the first
         file extension added from UI (DB does not support multiple file extensions)
         */
        dbconn.insertIntoPrinter(deviceName, deviceTrack);

        for (String ext : fileExt)
        {
            dbconn.insertIntoAcceptedFiles(deviceName, ext);
        }

        for (String trackableField : fieldNames)
        {
            boolean isNumerical = deviceModel.getFieldType(trackableField) == Device.TYPE_DOUBLE;
            dbconn.insertIntoCustom(deviceName, trackableField, isNumerical);
        }

        dbconn.closeDBConnection();
        return true;
    }

    private static DefaultListModel returnClassesArray(boolean status)
    {
        SQLMethods dbconn = new SQLMethods();
        ResultSet result2 = dbconn.getClasses(status);
        DefaultListModel classList = new DefaultListModel();;
        try
        {
            while (result2.next())
            {
                classList.addElement(result2.getString("class_id") + " " + result2.getString("class_name") + " " + result2.getString("class_section"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(BuildView.class.getName()).log(Level.SEVERE, null, ex);
        }

        dbconn.closeDBConnection();
        return classList;
    }

    public static DefaultListModel returnNonCurrentClasses()
    {
        return returnClassesArray(false);
    }

    public static DefaultListModel returnCurrentClasses()
    {
        return returnClassesArray(true);
    }

    public static void insertNewClass(String className, String classNumber, String sectionNumber, String professor)
    {
        SQLMethods dbconn = new SQLMethods();
        System.out.println(className + " " + classNumber + " " + sectionNumber + " " + professor);
        dbconn.insertIntoClasses(className + " " + classNumber, sectionNumber, professor);

        dbconn.closeDBConnection();
    }

    public static boolean userIDExist(String studentId, String studentPass)
    {
    	Hashtable<String, String> env = new Hashtable<String, String>();
    	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    	env.put(Context.SECURITY_AUTHENTICATION, "simple");
    	env.put(Context.PROVIDER_URL, "ldap://Towson.edu:389/");

    	// The value of Context.SECURITY_PRINCIPAL must be the logon username with the domain name
    	env.put(Context.SECURITY_PRINCIPAL, "TOWSONU\\" + studentId);

    	// The value of the Context.SECURITY_CREDENTIALS should be the user's password
    	env.put(Context.SECURITY_CREDENTIALS, studentPass);

    	DirContext ctx;
    	System.out.print("Attempting login with credentials: \n username: " + studentId + "\n password: " + studentPass);
    	try {
    	    // Authenticate the logon user
    	    ctx = new InitialDirContext(env);
    	    if(ctx != null)
    	    {
    	    	System.out.println("user is authenticated. grabbing first/last name");
    	    	/**
    	    	 *
    	    	 * @author Ryan
    	    	 */
    	    	String getUserInfo = "cmd /c net user "+studentId+" /domain |find /i \"full name\"";
    	    	studentFname = "debug_failed_fname"; // if these are not overwritten then couldn't grab first / last name from server
    	    	studentLname = "debug_failed_lname"; // but it shouldn't break the submit system ~Alex
    	    	Process p = null;
    	        
    	        try
    	        {
    	           p = Runtime.getRuntime().exec(getUserInfo);
    	           //System.out.println(p.toString());
    	           
    	        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
    	        String s = null;
    	        while ((s = stdInput.readLine()) != null) 
    	        {
    	            String delims ="[ ,]+";
    	            String[] tokens = s.split(delims);
    	            for (int i=2;i<tokens.length; i++)
    	            {
    	   	               System.out.println(tokens[i]);
    	               setStudentLName(tokens[2]);
    	               setStudentFName(tokens[3]);
 
    	            }
    	        }

    	        
    	        }
    	        catch(Exception e)
    	        {
    	            System.out.println("Something went wrong ");    
    	        }
                
                SQLMethods dbconn = new SQLMethods();
                ResultSet queryResult = dbconn.getStudentName(studentId);
                try{
                    while(queryResult.next())
                    {
                        studentFname  = queryResult.getString(1);
                        studentLname = queryResult.getString(2);
                    }
                } catch(Exception e)
                    {
                                System.out.println("Error: " + e);
                    }

                setStudentFName(studentFname);
                setStudentLName(studentLname);
                
    	    	if(studentFname.equals("debug_failed_fname"))
    	    	{
    	    		System.out.println("Failed to grab student data.");
    	    		return false;
    	    		
                        
    	    	}
    	    	else if(!checkUser(studentId)) // user isn't in db
	    		{
	    			addUser(studentId, studentFname, studentLname, studentId+"@students.towson.edu");
	    			System.out.println("Added new student: " + studentFname  + " " + studentLname + "successfully.");
	    			return true;
	    		}
	    		else // user already in db, can login and continue
	    		{
	    			System.out.println("Student: " + studentFname  + " " + studentLname + " already in database. Skipping entry and logging in.");
	    			return true;
	    		}
    	    }

    	} catch (NamingException ex) {
            return false;
    	}
    	//*****
        return true;
    }
 

    
    public static int addUser(String studentId, String firstname, String lastname, String email)
    {
        if (checkUser(studentId))
        {
            return -25;
        }
        SQLMethods dbconn = new SQLMethods();
        int flag = dbconn.insertIntoUsers(studentId, firstname, lastname, email);
        dbconn.insertIntoMaterial(studentId);
        dbconn.closeDBConnection();

        return flag;
    } 
    
    public static boolean checkUser(String studentID)
    {
       	// Old Method to verify student accounts
    	boolean flag = false;
        SQLMethods dbconn = new SQLMethods();
        ResultSet usersCountQuery = dbconn.checkUserExists(studentID);
        try
        {
            flag = usersCountQuery.next();
        } catch (SQLException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dbconn.closeDBConnection();
        return flag;   
    	
    	
    	
    }
    
    
    
    
    

    /*public static int updateUser(String studentId, String firstname, String lastname, String email)
    {
        int flag = -1;
        if (userIDExist(studentId))
        {
            SQLMethods dbconn = new SQLMethods();
            flag = dbconn.insertIntoUsers(studentId, firstname, lastname, email);
            dbconn.closeDBConnection();
            return flag;
        }
        return flag;
    }*/

    public static ArrayList<ArrayList<Object>> returnApprovedStudentSubmissionsForDevice(String printerName)
    {
        SQLMethods dbconn = new SQLMethods();
        ResultSet queryResult = dbconn.searchJobsStatusPrinter("approved", printerName);
        ArrayList<ArrayList<Object>> parsedResult = readyOutputForViewPage(queryResult);
        dbconn.closeDBConnection();
        return parsedResult;
    }

    public static boolean submitBuild(ArrayList<Integer> selectedStudentSubmissionFiles, Device deviceModel, String filePathToBuildFile, int models)
    {
        ArrayList<String> deviceFieldNames;
        SQLMethods dbconn = new SQLMethods();
        ResultSet fileInfo;
        String deviceName, jobFileName, previousFilePath;

        deviceName = deviceModel.getDeviceName();
        deviceFieldNames = deviceModel.getFieldNames();

        /* Set location for currentJob that have been printed */
        File newDir = new File(FileManager.getDevicePrinted(deviceName));

        /* STEP 1.0 - Add new build to printer build table in database */
        int runtime;
        /*
        runtime = convertToSeconds(deviceModel.getFieldData("Hours"), deviceModel.getFieldData("Minutes"), deviceModel.getFieldData("Seconds"));
        */
        //Russell - Joe
        //Started commenting out anything that needed info for the runtime input
        //Rajewski
        //no need for runtime
        /*if (deviceModel.getFieldType("Run time") == Device.TYPE_STRING)
        {
            runtime = Integer.parseInt((String) deviceModel.getFieldData("Run time"));
        } else
        {
            runtime = (Integer) deviceModel.getFieldData("Run time");
        }
        */
        
        //Russell - Joe
        //This function requires runtime to be a part of a build
        //The first call that is commented out here was removed for that reason. I stopped here
        //and then messaged you on Slack about this whole ordeal at this time before changing that whole function around
        //Rajewski
        //Removing runtime, adjust function insertIntoBuild
        //dbconn.insertIntoBuild(filePathToBuildFile.replace("\\", "\\\\"), runtime, models, deviceName);
        dbconn.insertIntoBuild(filePathToBuildFile.replace("\\", "\\\\"),  models, deviceName);
        /* Remove first entry becuase it is now not needed (HOTFIX) */
        //Rajewski
        //Removed Runtime
        //deviceModel.rmField("Run time");
        /* STEP 1.1 - Insert all build data for the device into the database for the assoicated printer and trackable field for that printer */
        //Rajewski
        //I don't think we need this for loop since there is no more input table. Hotfix 11/21/
        /*
        for (String fieldName : deviceFieldNames)
        {
            //Rajewski
            //for development purposes
            System.out.println(deviceFieldNames);
            dbconn.insertIntoColumnBuildData(deviceName, fieldName, "" + deviceModel.getFieldData(fieldName), filePathToBuildFile.replace("\\", "\\\\"));
        }
        */
        /* Loop through currentJob that where checked in as part of the build and update data related to student submissions */
        for (Integer currentJob : selectedStudentSubmissionFiles)
        {
            try
            {
                /* STEP 2 - get file information about current job */
                fileInfo = dbconn.selectFileInfo(currentJob);
                if (fileInfo.next())
                {
                    jobFileName = fileInfo.getString("file_name");
                    previousFilePath = fileInfo.getString("file_path");
                    previousFilePath = previousFilePath.replace("\\", "\\\\");

                    /* STEP 3 - Move file to Printed location and update file reference in the database */
                    if (FileManager.doesFileExist(previousFilePath))
                    {
                        /* 
                         STEP 3.0 - Move file to "Printed" directory 
                         STEP 3.1 - Update file location info in database
                         STEP 3.2 - Update build_name info in job table
                         */
                        FileUtils.moveFileToDirectory(new File(previousFilePath), newDir, true);
                        String newFileLocation = FileManager.getDevicePrinted(deviceName) + "\\" + jobFileName;
                        System.out.println(newFileLocation);
                        dbconn.updateJobFLocation(currentJob, newFileLocation.replace("\\", "\\\\"));
                        dbconn.updateJobBuildName(filePathToBuildFile.replace("\\", "\\\\"), currentJob);

                    } else
                    {
                        /* STEP 3.2 - CASE WHERE FILE DOES NOT EXIST - Update file location in database to null */
                        dbconn.updateJobFLocation(currentJob, "");
                    }

                    /* STEP 4 - Set job status to completed */
                    dbconn.changeJobStatus(currentJob, "completed");

                } else
                {
                    dbconn.closeDBConnection();
                    return false;
                }
                /* 
                 Need to add code to handle one of these two excpetions. They might have differentw ways of handling reverting updated data thats
                 why there are two catch blocks.
                 */
            } catch (IOException ex)
            {
                Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex)
            {
                Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dbconn.closeDBConnection();
        return true;
    }
    
    public static int convertToSeconds(int h, int m, int s)
    {
        int sec = (h*3600) + (m*60) + s;
        return sec;
    }

    public static boolean findAndVerifyFile(String file){
    
        SQLMethods dbconn = new SQLMethods();
        ResultSet queryResult = dbconn.selectBuildLocation(file);
    
        ArrayList<ArrayList<Object>> retval = readyOutputForViewPage(queryResult);
        
        System.out.println(retval.get(0).get(0));
        
        if(new File((String)(retval.get(0).get(0))).exists()){
        
            dbconn.closeDBConnection();
            return true;
        }
        else{
            dbconn.closeDBConnection();
            return false;
        }
    }
    
    /**
     * Creates template device class for the printer build process to use. This
     * is created before build data is typed in in the printer dialog class.
     * This device class will be used to determine the fields that will be
     * needed for the printer dialog, as well as a middleman for the ui and
     * database (so they don't have to mix).
     *
     * @param printer the name of the printer (in the database)
     * @return
     */
    public static Device getPrinterInfo(String printer)
    {
        Device buildPrinter = new Device();
        SQLMethods dbconn = new SQLMethods();

        /* Query the database for the device column names and its data type (do not be confused with stored data type) */
        ResultSet queryResultDeviceColumnInfo = dbconn.selectDeviceTrackableMetaData(printer);
        ResultSet studentSubmissionQuery = dbconn.getStudentSubmissionStatusFromDevice(printer);

        buildPrinter.setDeviceName(printer);

        try
        {
            /* Set our device student submission status here */
            if (studentSubmissionQuery.next())
            {
                buildPrinter.setTrackSubmission(studentSubmissionQuery.getBoolean("student_submission"));
            }

            /* Add the data from the query by row into the Device class */
            while (queryResultDeviceColumnInfo.next())
            {
                Object tmpValue;

                if ((Boolean) queryResultDeviceColumnInfo.getBoolean("numerical"))
                {
                    tmpValue = 0;
                } else
                {
                    tmpValue = "";
                }

                buildPrinter.addField(queryResultDeviceColumnInfo.getString("custom_field_name"), tmpValue);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(UtilController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dbconn.closeDBConnection();
        return buildPrinter;
    }

    public static boolean isBuildFileLocationUsed(String flocation)
    {
        SQLMethods dbconn = new SQLMethods();

        boolean doesFileExist = dbconn.doesBuildFileLocationExist("printer_build", flocation) > 0;
        dbconn.closeDBConnection();

        return doesFileExist;
    }

    public static void removeClass(int classId)
    {
        SQLMethods dbconn = new SQLMethods();
        dbconn.deleteFromClass(classId);
        dbconn.closeDBConnection();
    }

    public static void removeDevice(String device)
    {

        SQLMethods dbconn = new SQLMethods();
        dbconn.deletePrinter(device);
        dbconn.closeDBConnection();

    }

    public static void removePrinterBuild(String buildname)
    {
        SQLMethods dbconn = new SQLMethods();
        try
        {
            ArrayList<String> jobNames;
            String printer;
            ResultSet jobs;
            jobs = dbconn.searchJobsByBuildName(buildname);

            while (jobs.next())
            {
                int currentJob = jobs.getInt("submission_id");
                String deviceName = jobs.getString("printer_name");
                
                String jobFileName = jobs.getString("file_name");
                String previousFilePath = jobs.getString("file_path");

                /* STEP 3 - Move file to Printed location and update file reference in the database */
                if (FileManager.doesFileExist(previousFilePath))
                {
                    /* 
                     STEP 3.0 - Move file to "ToPrinted" directory 
                     STEP 3.1 - Update file location info in database
                     */
                    String newFileLocation = FileManager.getDeviceToPrint(deviceName) + "\\" + jobFileName;
                    FileUtils.moveFileToDirectory(new File(previousFilePath), new File(newFileLocation), true);
                    dbconn.updateJobFLocation(currentJob, newFileLocation.replace("\\", "\\\\"));

                } else
                {
                    /* STEP 3.2 - CASE WHERE FILE DOES NOT EXIST - Update file location in database to null */
                    dbconn.updateJobFLocation(currentJob, "");
                }
                
                dbconn.updateStatus("approved", currentJob);
                dbconn.deleteByBuildId(buildname);
            }
            
            dbconn.closeDBConnection();

        } catch (Exception e)
        {
            dbconn.closeDBConnection();
        }
    }

}
