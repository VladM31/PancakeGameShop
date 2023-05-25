using UnityEngine;

public class LightIntensityController : MonoBehaviour
{
    public Light targetLight; // ������ �� ��������� Light, ������� �� ������ ��������

    public float targetIntensity = 0.15f; // �������� �������� ������������� �����
    public float transitionDuration = 5f; // ������������ �������� � ��������

    private float initialIntensity = 1f; // ��������� ������������� �����

    private void Start()
    {
        initialIntensity = targetLight.intensity; // ��������� ��������� ������������� �����
        StartCoroutine(ChangeIntensityOverTime());
    }

    private System.Collections.IEnumerator ChangeIntensityOverTime()
    {
        float elapsedTime = 0f;

        while (elapsedTime < transitionDuration)
        {
            // ��������� ������� ������������� �� ������ ��������� � �������� �������������
            float currentIntensity = Mathf.Lerp(initialIntensity, targetIntensity, elapsedTime / transitionDuration);

            // ���������� ������� ������������� �����
            targetLight.intensity = currentIntensity;

            elapsedTime += Time.deltaTime;
            yield return null;
        }

        // ���������� �������� ������������� �����, ����� �������� ������ ����������
        targetLight.intensity = targetIntensity;
    }
}