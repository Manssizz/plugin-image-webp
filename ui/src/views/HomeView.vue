<script setup lang="ts">
import { ref } from 'vue'

const settings = ref({
  watermarkType: 'text',
  textContent: 'Sample Watermark',
  textColor: '#ffffff',
  textSize: 20,
  textPosition: 'bottom-right',
  imagePath: '',
  imageTransparency: 0.5,
  imageSize: 20,
  imagePosition: 'bottom-right',
  webpQuality: 0.8,
  webpCompression: true,
  convertHeaderImage: true
})

const saveSettings = () => {
  // Placeholder: Save settings to backend
  console.log('Settings saved:', settings.value)
}

const handleFileUpload = (event: Event) => {
  const target = event.target as HTMLInputElement
  if (target.files && target.files[0]) {
    settings.value.imagePath = target.files[0].name // Or handle upload to server
  }
}
</script>

<template>
  <section id="image-webp-settings">
    <div class="wrapper">
      <h1 class="title">Image WebP Converter Settings</h1>
      <form @submit.prevent="saveSettings" class="settings-form">
        <div class="group">
          <h2>Watermark Settings</h2>
          <div class="form-group">
            <label for="watermarkType">Watermark Type</label>
            <select id="watermarkType" v-model="settings.watermarkType">
              <option value="text">Text</option>
              <option value="image">Image</option>
            </select>
          </div>
          <div v-if="settings.watermarkType === 'text'">
            <div class="form-group">
              <label for="textContent">Text Content</label>
              <input type="text" id="textContent" v-model="settings.textContent" />
            </div>
            <div class="form-group">
              <label for="textColor">Text Color</label>
              <input type="color" id="textColor" v-model="settings.textColor" />
            </div>
            <div class="form-group">
              <label for="textSize">Text Size</label>
              <input type="number" id="textSize" v-model.number="settings.textSize" min="10" max="100" />
            </div>
            <div class="form-group">
              <label for="textPosition">Text Position</label>
              <select id="textPosition" v-model="settings.textPosition">
                <option value="top-left">Top Left</option>
                <option value="top-right">Top Right</option>
                <option value="bottom-left">Bottom Left</option>
                <option value="bottom-right">Bottom Right</option>
                <option value="center">Center</option>
              </select>
            </div>
          </div>
          <div v-if="settings.watermarkType === 'image'">
            <div class="form-group">
              <label for="imagePath">Watermark Image</label>
              <input type="file" id="imagePath" accept=".png" @change="handleFileUpload" />
            </div>
            <div class="form-group">
              <label for="imageTransparency">Image Transparency (0-1)</label>
              <input type="number" id="imageTransparency" v-model.number="settings.imageTransparency" min="0" max="1" step="0.1" />
            </div>
            <div class="form-group">
              <label for="imageSize">Image Size (%)</label>
              <input type="number" id="imageSize" v-model.number="settings.imageSize" min="1" max="100" />
            </div>
            <div class="form-group">
              <label for="imagePosition">Image Position</label>
              <select id="imagePosition" v-model="settings.imagePosition">
                <option value="top-left">Top Left</option>
                <option value="top-right">Top Right</option>
                <option value="bottom-left">Bottom Left</option>
                <option value="bottom-right">Bottom Right</option>
                <option value="center">Center</option>
              </select>
            </div>
          </div>
        </div>
        <div class="group">
          <h2>WebP Settings</h2>
          <div class="form-group">
            <label for="webpQuality">WebP Quality (0-1)</label>
            <input type="number" id="webpQuality" v-model.number="settings.webpQuality" min="0" max="1" step="0.1" />
          </div>
          <div class="form-group">
            <label for="webpCompression">
              <input type="checkbox" id="webpCompression" v-model="settings.webpCompression" />
              Enable WebP Compression
            </label>
          </div>
        </div>
        <div class="group">
          <h2>General Settings</h2>
          <div class="form-group">
            <label for="convertHeaderImage">
              <input type="checkbox" id="convertHeaderImage" v-model="settings.convertHeaderImage" />
              Convert Header Images
            </label>
          </div>
        </div>
        <button type="submit" class="save-btn">Save Settings</button>
      </form>
    </div>
  </section>
</template>

<style lang="scss" scoped>
#image-webp-settings {
  padding: 2rem;
  background-color: #f8fafc;
  min-height: 100vh;
}

.wrapper {
  max-width: 800px;
  margin: 0 auto;
  background: #fff;
  padding: 2rem;
  border-radius: 0.5rem;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);

  .title {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 2rem;
    text-align: center;
  }

  .settings-form {
    .group {
      margin-bottom: 2rem;
      padding: 1rem;
      border: 1px solid #e5e7eb;
      border-radius: 0.375rem;

      h2 {
        font-size: 1.25rem;
        font-weight: 600;
        margin-bottom: 1rem;
        color: #374151;
      }

      .form-group {
        margin-bottom: 1rem;

        label {
          display: block;
          font-weight: 500;
          margin-bottom: 0.5rem;
          color: #4b5563;
        }

        input, select {
          width: 100%;
          padding: 0.5rem;
          border: 1px solid #d1d5db;
          border-radius: 0.25rem;
          font-size: 0.875rem;
        }

        input[type="checkbox"] {
          width: auto;
          margin-right: 0.5rem;
        }
      }
    }

    .save-btn {
      background-color: #3b82f6;
      color: #fff;
      padding: 0.75rem 1.5rem;
      border: none;
      border-radius: 0.375rem;
      font-size: 1rem;
      font-weight: 500;
      cursor: pointer;
      transition: background-color 0.2s;

      &:hover {
        background-color: #2563eb;
      }
    }
  }
}
</style>
