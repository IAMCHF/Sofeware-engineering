package dhu.cst.humanresource.service.impl;

import dhu.cst.humanresource.dao.HistoryMapper;
import dhu.cst.humanresource.entity.History;
import dhu.cst.humanresource.entity.Hr;
import dhu.cst.humanresource.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryMapper historymapper;

    @Override
    public void inserthistory(History employee) {
        historymapper.insert(employee);
    }

    @Override
    public List<History> selcetallhistory() {
        return historymapper.selectAll();
    }
}
