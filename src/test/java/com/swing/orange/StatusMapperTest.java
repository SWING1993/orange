package com.swing.orange;

import com.swing.orange.entity.Status;
import com.swing.orange.mapper.StatusMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatusMapperTest {

    @Autowired
    StatusMapper statusMapper;

    @Test
    public void insertTest() {
        Status status = new Status(36, 0, "CC", "https://cc.png", "哈哈，今天捡了100w人民币", "", "", 0, "iPhone X");
        this.statusMapper.insert(status);
    }

    @Test
    public void getAllTest() {
        List<Status> list = this.statusMapper.getAll();
        System.out.println(list);
    }

    @Test
    public void selectByUidTest() {
        List<Status> statuses = this.statusMapper.getAll();
        Status status = statuses.get(statuses.size()-1);
        List<Status> list = this.statusMapper.selectByUid(status.getUid());
        System.out.println(list);
    }

    @Test
    public void deleteByIdTest() {
        List<Status> statuses = this.statusMapper.getAll();
        Status status = statuses.get(0);
        this.statusMapper.deleteById(status.getId());
    }
}

