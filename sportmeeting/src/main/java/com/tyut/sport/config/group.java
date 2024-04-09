package com.tyut.sport.config;

import com.tyut.sport.pojo.Sign;

import java.util.*;


public class group {
    public static List<Sign> groupAndAssign(List<Sign> signs) {
        // 创建一个Map用来存储每个项目的报名信息
        Map<Integer, List<Sign>> groupedByProject = new HashMap<>();

        // 将报名信息按项目ID分组
        for (Sign sign : signs) {
            if (!groupedByProject.containsKey(sign.getProjectId())) {
                groupedByProject.put(sign.getProjectId(), new ArrayList<>());
            }
            groupedByProject.get(sign.getProjectId()).add(sign);
        }

        // 清空原来的报名信息列表
        signs.clear();

        // 在每个项目中按学生ID排序并分配跑道
        for (List<Sign> projectSigns : groupedByProject.values()) {
            // 按学生ID排序
            projectSigns.sort(Comparator.comparingInt(Sign::getSporterId));

            // 每8个学生创建一个新的组，并分配跑道
            int groupNumber = 1;
            for (int i = 0; i < projectSigns.size(); i++) {
                if (i % 8 == 0 && i != 0) {
                    groupNumber++;
                }
                projectSigns.get(i).setGrou( groupNumber + "组");
                projectSigns.get(i).setWhich(String.valueOf(i % 8 + 1));
            }

            // 将分组和分配跑道后的结果放回原来的数据结构中
            signs.addAll(projectSigns);
        }
        return signs;
    }
}