package cs2321;
import net.datastructures.*;

/**
 * Samuel Milner
 * @author Ruihong Zhang
 * Reference: Text book: R14.17 on page 678
 */
public class Course {

    Graph<String, Integer> courseMap;

    /**
     * @param courses: An array of course information. Each element in the array is also array:
     *                 starts with the course name, followed by a list (0 or more) of prerequisite course names.
     */
    public Course(String courses[][]) {
        //TODO: complete the constructor
        courseMap = new AdjListGraph<>(true);

        for (String[] s : courses) {
            Vertex<String> v = courseMap.insertVertex(s[0]);
            if (s.length > 1) {
                for (int i = s.length - 1; i > 0; i--) {
                    courseMap.insertEdge(courseMap.insertVertex(s[i]), v, 1);
                }
            }
        }
    }

    /**
     * @param course
     * @return find the earliest semester that the given course could be taken by a students after taking all the prerequisites.
     */
    public int whichSemester(String course) {

        Vertex<String> u = null;
        for (Vertex<String> v : courseMap.vertices()) {
            if (v.getElement().equals(course)) u = v;
        }
        return backwards(u, 0);
    }

    public int backwards(Vertex<String> course, int semesters) {
        for (Edge<Integer> e : courseMap.incomingEdges(course)) {
            Vertex<String> v = courseMap.opposite(course, e);
            if (courseMap.incomingEdges(v) != null) {
                semesters++;
                return backwards(v, semesters);
            }
        }
        return semesters += 1;
    }
}
