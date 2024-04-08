/**
 * @author <Le Ngoc Hieu - s3927205>
 */

import java.util.ArrayList;

public interface ClaimProcessManager {
    public void addOne();
    public void deleteOne(String claimID);
    public Claim getOne();
    public ArrayList<Claim> getAll();
}
