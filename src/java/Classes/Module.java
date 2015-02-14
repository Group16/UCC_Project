package Classes;


/**
 *
 * @author vnl1
 */
public class Module
{
    private int moduleID;
    private String moduleName;
    private int courseName;
    
    public Module(int moduleID,String moduleName,int courseName)
    {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.courseName = courseName;
    }
    
    public int getModuleID ()
    {
        return moduleID;    
    }
    
    public String getModuleName()
    {
        return moduleName;
    }
    
    public int getCourseName()
    {
        return courseName;    
    }
            
    
}
