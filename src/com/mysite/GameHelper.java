package com.mysite;

import java.util.ArrayList;
import java.util.List;

public class GameHelper{
    public List<Integer> moveAndMergeEqual(List<Integer> list)
    {
        List<Integer> res = moveNotNull(list);
        return mergeEqual(res);
    }

    private List<Integer> moveNotNull(List<Integer> list)
    {
        List<Integer> resList = new ArrayList<>();

        //заполняем результирующий список null'ами
        for(int i=0; i<list.size();i++)
        {
            resList.add(null);
        }

        //текущий индекс в результирующем списке
        int indexResList=0;

        //смещаем ненулевые элементы влево
        for (int i=0; i<list.size(); i++)
        {
            if(list.get(i)!=null)
            {
                resList.set(indexResList,list.get(i));
                indexResList++;
            }

        }

        return resList;
    }

    private List<Integer> mergeEqual(List<Integer> list)
    {
        List<Integer> resList = new ArrayList<>();

        //заполняем результирующий список null'ами
        for(int i=0; i<list.size();i++)
        {
            resList.add(null);
        }

        //текущий индекс в результирующем списке
        int indexResList=0;

        int iL=0; //индекс левой переменной
        int iR=1; //индекс правой переменной

        while (iR<list.size())
        {
            if(list.get(iL)==null)
                break;
            else if (list.get(iR)==null)
            {
                resList.set(indexResList,list.get(iL));
                break;
            }
            else if (list.get(iL).equals(list.get(iR)))
            {
                resList.set(indexResList,2*list.get(iR));
                indexResList++;
                iL+=2;
                iR+=2;
            }
            else
            {
                resList.set(indexResList,list.get(iL));
                indexResList++;
                resList.set(indexResList,list.get(iR));
                indexResList++;
                iL+=2;
                iR+=2;
            }
        }
        return resList;
    }
}
