import java.nio.charset.Charset;
import java.util.List;

/** ��������� ������ �� ������ � �������� � �� ���������. */
interface CuttingStringEncoder {

    /**
     * ��������� ������ <code>source</code> �� ��� ����� ������� ���������� ���������
     * ��� ����� ������� �����, �� ����������� �����������.
     * <p/>
     * ���� ���������� ����� ������ ��������, �� � ������ ������� ����������� ������: ����� �X/Y � (��� �������,
     * ����� X, ����, ����� Y, ������), ��� X � ����� ��������, Y � ����� ���������� ���������.
     * <p/>
     * ������ ����������� �� �������� �� ����������� �� ��������; ������ ���� ����� �����
     * ��������� ����� �������� (� ������ �������), ����� ����� ���������. ���������� ������� �� ������� ���������
     * �������������. ������������� ���������� ������� ����������� �� ������.
     * <p/>
     * ���������� ������������ �������� � ��������� ���������.
     * ����� ������� �������� � �������� � ��������� <code>charset</code> �� ��������� <code>maxSegLen</code> ������.
     * <p/>
     * ���������� ��������� ������ ���� �� ���, ��� �� �����������.
     * <p/>
     * ���� � ����������� ����������� ������������ �������� ������ ����������,
     * ���������� {@link IllegalArgumentException} � ������������.
     *
     * @param source    �������� ������
     * @param charset   ���������
     * @param maxSegLen ������������ ����� �������� � ������
     * @return �������� ������ � ��������� ���������
     */
	List<byte[]> cutAndEncode(String source, Charset charset, int maxSegLen) throws IllegalArgumentException;
}