package didi2;

import java.util.ArrayList;
import java.util.List;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        System.out.println(object.getClass().getName());
        if (object.getClass().getName().equals("java.util.ArrayList")) {
            List list = (ArrayList)object;
            resultVO.setCount(list.size());
        }

        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


}

