<template>
	<div class="title">
		<slot/>
	</div>
</template>

<script>
  export default {
    name: "m_title"
  }
</script>

<style scoped>
	.title {
		padding-left: 1em;
		border-left: 3px solid rgb(24, 144, 255);
	}
</style>
