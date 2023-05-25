using UnityEngine;

public class LightIntensityController : MonoBehaviour
{
    public Light targetLight; // Ссылка на компонент Light, который вы хотите изменить

    public float targetIntensity = 0.15f; // Желаемая конечная интенсивность света
    public float transitionDuration = 5f; // Длительность перехода в секундах

    private float initialIntensity = 1f; // Начальная интенсивность света

    private void Start()
    {
        initialIntensity = targetLight.intensity; // Запомнить начальную интенсивность света
        StartCoroutine(ChangeIntensityOverTime());
    }

    private System.Collections.IEnumerator ChangeIntensityOverTime()
    {
        float elapsedTime = 0f;

        while (elapsedTime < transitionDuration)
        {
            // Вычислить текущую интенсивность на основе начальной и конечной интенсивности
            float currentIntensity = Mathf.Lerp(initialIntensity, targetIntensity, elapsedTime / transitionDuration);

            // Установить текущую интенсивность света
            targetLight.intensity = currentIntensity;

            elapsedTime += Time.deltaTime;
            yield return null;
        }

        // Установить конечную интенсивность точно, чтобы избежать ошибки округления
        targetLight.intensity = targetIntensity;
    }
}