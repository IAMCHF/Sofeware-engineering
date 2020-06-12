package dhu.cst.humanresource.service;

import dhu.cst.humanresource.entity.History;
import dhu.cst.humanresource.entity.Hr;

import java.util.List;

public interface HistoryService {
    void inserthistory(History employee);
    List<History>selcetallhistory();
}
