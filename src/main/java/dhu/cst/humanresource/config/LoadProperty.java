package dhu.cst.humanresource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Configuration

@PropertySource(value = "classpath:hrbasicwage.properties")
@ConfigurationProperties(prefix = "wa")
@Component
public class LoadProperty {
    @Value("${wa.generalma}")
    private String gemanager ;
    @Value("${wa.ma}")
    private String  manager;
    @Value("${wa.teamle}")
    private String grleader;
    @Value("${wa.groupmen}")
    private String groupmember;
    public String getGemanager()
    {
        return gemanager;
    }
    public String getManager()
    {
        return manager;
    }
    public String getGroupmember()
    {
        return groupmember;
    }
    public String getGrleader()
    {
        return grleader;
    }
    public void setGemanager(String gemanager)
    {
        this.gemanager=gemanager;
    }
    public void setManager(String manager)
    {
        this.manager=manager;
    }
    public void setGrleader(String grleader)
    {
        this.grleader=grleader;
    }
    public void setGroupmember(String groupmember)
    {
        this.groupmember=groupmember;
    }


}
