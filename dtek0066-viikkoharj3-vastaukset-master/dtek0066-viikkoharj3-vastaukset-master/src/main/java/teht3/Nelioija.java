package teht3;

import java.util.function.Function;

class Nelioija implements Function {

    @Override
    public Object apply(Object o) {
        return Math.pow((Integer) o, 2);
    }
}

