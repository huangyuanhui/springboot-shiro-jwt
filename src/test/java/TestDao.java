import com.cmdc.ShiroApplication;
import com.cmdc.domain.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;

/**
 * @author : wuwensheng
 * @date : 17:44 2020/7/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShiroApplication.class)
public class TestDao {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
        LinkedHashMap<String, Object> stringObjectLinkedHashMap = userMapper.selectUserPermissionById("10001");
        System.out.println(stringObjectLinkedHashMap);
    }


}
