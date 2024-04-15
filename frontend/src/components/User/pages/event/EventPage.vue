<script setup>
import { ref } from 'vue'
import VMdPreview from '@kangc/v-md-editor/lib/preview'
import Comment from '@/components/Modules/comment/Comment.vue'
import Avatar from '@/components/Modules/avatar/Avatar.vue'


let title = ref('')
let eventName = ref('')
let authorId = ref('')
let authorName = ref('')
let applyStartTime = ref('')
let applyEndTime = ref('')
let startTime = ref('')
let endTime = ref('')
let score = ref(0)
let posterUrl = ref('')
let text = ref('')
let postList = ref([])

// just for test
title = '某某活动马上就要开始了！'
eventName = '活动某某'
authorId = '123456'
authorName = 'Lamptales'
applyStartTime = '2024-4-4 00:00:00'
applyEndTime = '2024-4-14 00:00:00'
startTime = '2024-4-16 00:00:00'
endTime = '2024-4-26 00:00:00'
score = '4'
posterUrl = 'https://static.fotor.com.cn/assets/projects/pages/c3000361e65b4048ab8dd18e8c076c0e/fotor-86b1e566f1d74bf1870ac2c2a624390f.jpg'

let stars = ref("")
stars = '⭐'
for (let i = 1; i < score; i++) {
    stars = stars + '⭐'
}

text = 'sdf\n' +
    '### Title\n' +
    '\n' +
    '![Description](https://github.com/LampTales/YuxiaLin/raw/main/pics/lin.jpg){{{width="200" height="auto"}}}'

let test_text = ref('')
test_text = '<p align="left">\n' +
    '    English ｜ <a href="README.md">中文</a>\n' +
    '</p>\n' +
    '<br>\n' +
    '\n' +
    '<h1 align="center">\n' +
    '  Llama-Chinese\n' +
    '</h1>\n' +
    '<p align="center" width="100%">\n' +
    '  <img src="https://github.com/LampTales/YuxiaLin/raw/main/pics/lin.jpg" alt="Llama" style="width: 20%; display: block; margin: auto;"></a>\n' +
    '</p>\n' +
    '<p align="center">\n' +
    '  <font face="黑体" color=orange size="6"> The Best Chinese Llama Large Language Model </font>\n' +
    '</p>\n' +
    '<p align="center">\n' +
    '  <a href="https://llama.family">Online: llama.family</a>\n' +
    '</p>\n' +
    '<p align="center">\n' +
    '  <a href="https://huggingface.co/FlagAlpha/Atom-7B-Chat">Open-source Chinese Pre-trained LLM Atom based on Llama2</a>\n' +
    '</p>\n' +
    '\n' +
    '</br></br>\n' +
    '\n' +
    '\n' +
    '## 🗂️ Content Guide\n' +
    '- [🔥 Community Introduction: Chinese Llama Community](#-community-introduction-llama-chinese-community)\n' +
    '- [📢 Community Announcements](#-community-announcements)\n' +
    '- [🐼 Latest Downloads of Llama2](#-latest-downloads-of-llama2-in-china)\n' +
    '- [🔵 Atom LLM](#-atom-large-models)\n' +
    '  - [Large-scale Chinese Data Pretraining](#large-scale-chinese-data-pretraining)\n' +
    '  - [More Efficient Chinese Vocabulary](#more-efficient-chinese-vocabulary)\n' +
    '  - [Adaptive Context Expansion](#adaptive-context-expansion)\n' +
    '- [📝 Chinese Data](#-chinese-data)\n' +
    '- [⏬ Model Deployment](#-model-deployment)\n' +
    '  - [Model Downloads](#model-downloads)\n' +
    '    - [Meta Official Llama2 Model](#meta-official-llama2-model)\n' +
    '    - [Fine-tuned Chinese Models based on Llama2](#chinese-fine-tuned-models-based-on-llama2)\n' +
    '    - [Pre-trained Chinese Model Atom based on Llama2](#chinese-pre-trained-model-atom-based-on-llama2)\n' +
    '  - [Code Examples](#model-calling-code-examples)\n' +
    '  - [FastAPI Setup](#fastapi-interface-setup)\n' +
    '  - [Gradio Setup](#quick-qa-platform-setup-with-gradio)\n' +
    '  - [Docker Setup](#docker-deployment-of-qa-interface)\n' +
    '- [🤖 Model Pretraining](#-model-pretraining)\n' +
    '- [💡 Model Fine-tuning](#-model-fine-tuning)\n' +
    '  - [Step1: Environment Setup](#step1-environment-setup)\n' +
    '  - [Step2: Data Preparation](#step2-data-preparation)\n' +
    '  - [Step3: Fine-tuning Scripts](#step3-fine-tuning-script)\n' +
    '    - [LoRA Fine-tuning](#lora-fine-tuning)\n' +
    '    - [Full-parameter Fine-tuning](#full-parameter-fine-tuning)\n' +
    '  - [Step4: Load Fine-tuned Model](#step4-load-fine-tuned-model)\n' +
    '    - [LoRA Fine-tuning](#lora-fine-tuning-1)\n' +
    '    - [Full-parameter Fine-tuning](#full-parameter-fine-tuning-1)\n' +
    '- [🍄 Model Quantization](#-model-quantization)\n' +
    '- [🚀 Inference Acceleration](#-inference-acceleration)\n' +
    '  - [TensorRT-LLM](#TensorRT-LLM)\n' +
    '  - [vLLM](#vllm)\n' +
    '  - [JittorLLMs](#jittorllms)\n' +
    '  - [lmdeploy](#lmdeploy)\n' +
    '- [🥇 Model Evaluation](#-model-evaluation)\n' +
    '- [💪 Extension Capabilities](#-extension-capabilities)\n' +
    '  - [LangChain](#langchain)\n' +
    '- [🐞 Code Model](#-code-model)\n' +
    '- [📖 Learning Resources](#-learning-resources)\n' +
    '  - [Meta Official Introduction to Llama2](#meta-official-introduction-to-llama2)\n' +
    '  - [Llama-related Papers](#llama-related-papers)\n' +
    '  - [Llama2 Evaluation Results](#llama2-evaluation-results)\n' +
    '- [🎉 Acknowledgments](#-acknowledgments)\n' +
    '- [🤔 Issue Feedback](#-issue-feedback)\n' +
    '\n' +
    '\n' +
    '\n' +
    '## 🔥 Community Introduction: Chinese Llama Community\n' +
    '\n' +
    'Welcome to the Chinese Llama Community! We are a technical community dedicated to optimizing and building on top of the Llama model for Chinese applications.\n' +
    '**\\*Based on large-scale Chinese data, we start pre-training and continuously upgrade the Llama2 model for Chinese capabilities\\***.\n' +
    'We warmly welcome developers and researchers passionate about LLM models to join our community.\n' +
    '\n' +
    '<details lang="en">\n' +
    '\n' +
    '### Why Choose the Chinese Llama Community?\n' +
    '🚀 **Support from a Team of Senior Engineers**: The community has a team of dedicated NLP senior engineers who provide strong technical support and rich experience to guide and assist you.\n' +
    '\n' +
    '🎯 **Chinese Optimization**: We focus on optimizing Llama2 for Chinese processing, exploring the best practices for Chinese to enhance its performance and adaptability.\n' +
    '\n' +
    '💡 **Innovative Exchange**: Our community includes a creative and experienced team of members who organize regular online events, technical discussions, and experience sharing to promote innovative exchanges.\n' +
    '\n' +
    '🌐 **Global Connectivity**: We welcome developers from around the world to join the community, creating an open and diverse platform for learning and communication.\n' +
    '\n' +
    '🤝 **Open Sharing**: We encourage community members to open-source and share code and models, promoting collaborative win-win efforts and advancing the development of Chinese NLP technology.\n' +
    '\n' +
    '### Community Activities\n' +
    '🗓️ **Online Lectures**: Inviting industry experts to conduct online lectures, sharing the latest technology and applications of Llama2 in the Chinese NLP field, and discussing cutting-edge research results.\n' +
    '\n' +
    '💻 **Project Showcase**: Members can showcase their project achievements in Llama2 Chinese optimization, receive feedback and suggestions, and promote project collaboration.\n' +
    '\n' +
    '📚 **Learning Resources**: The community maintains a rich library of learning materials, including tutorials, documentation, and paper interpretations, providing comprehensive learning support to members.\n' +
    '\n' +
    '📝 **Paper Interpretation**: Community members collectively interpret the latest research papers related to Llama2, delving into advanced algorithms and methods.\n' +
    '\n' +
    '🎉 **Themed Events**: Regularly organize various themed events, including challenges, hackathons, and technical salons, allowing community members to exchange and learn in a relaxed and enjoyable atmosphere.\n' +
    '\n' +
    '🌟 **Reward Program**: We have established a reward program to honor and reward members who actively participate and contribute outstanding work to the community, motivating more outstanding talents to join.\n' +
    '\n' +
    '📈 **Technical Consultation**: We provide technical consulting services to answer your questions and help you overcome challenges in the development and optimization of Llama2.\n' +
    '\n' +
    '🚀 **Project Collaboration**: Encourage collaboration between members on projects to explore the potential of Llama2 in practical applications and create innovative solutions.\n' +
    '\n' +
    '### Join Us Now!\n' +
    '📚 **Vision**: Whether you are a professional developer or researcher with experience in Llama2 or a newcomer interested in optimizing Llama2 for Chinese, we eagerly look forward to your joining. In the Chinese Llama Community, you will have the opportunity to exchange ideas with top talents in the industry, work together to advance Chinese NLP technology, and create a brighter technological future!\n' +
    '\n' +
    '🔗 **Friendly Reminder**: This community is a platform for professional technical exchange. We earnestly hope that like-minded developers and researchers join us. Please adhere to the community guidelines, maintain a positive learning atmosphere, and any content and advertisements unrelated to Llama2 will be removed. Thank you for your understanding and support!\n' +
    '\n' +
    '</details>\n' +
    '\n' +
    '## 📢 Community Announcements\n' +
    '\n' +
    '【Latest】October 8, 2023: Added the inference acceleration feature for JittorLLMs from Tsinghua University [JittorLLMs](#jittorllms)!\n' +
    '\n' +
    '【Latest】September 12, 2023: Updated pre-training versions [Atom-7B](https://huggingface.co/FlagAlpha/Atom-7B) and dialogue version [Atom-7B-Chat](https://huggingface.co/FlagAlpha/Atom-7B-Chat) model parameters. The latest Chinese pre-training data size is 100 billion tokens, and the training progress can be viewed at [llama.family](https://llama.family/)!\n' +
    '\n' +
    '【Latest】September 2, 2023: Added [pre-training code](#-model-pretraining) and [full-parameter fine-tuning code](#-model-fine-tuning)!\n' +
    '\n' +
    '【Latest】August 28, 2023: Released the open-source large model [Atom-7B](https://huggingface.co/FlagAlpha/Atom-7B) based on Llama2 for Chinese pre-training and will continue to be updated. Details can be found in the [community article](https://mp.weixin.qq.com/s/Bdx0JTVh1kgPn5ydYxIkEw)!\n' +
    '\n' +
    '【Latest】August 26, 2023: Provided [FastAPI](#fastapi-interface-setup) interface setup script!\n' +
    '\n' +
    '【Latest】August 26, 2023: Provided a script to convert Meta official model parameters to a format compatible with Hugging Face [Format Conversion Script](https://github.com/LlamaFamily/Llama-Chinese/blob/main/scripts/convert2hf/README.md)!\n' +
    '\n' +
    '【Latest】August 26, 2023: Added [Code Llama](#-code-model) model!\n' +
    '\n' +
    '<details lang="en">\n' +
    '\n' +
    '- August 15, 2023: Added [PEFT load fine-tuning model parameters](#load-fine-tuned-model) code example!\n' +
    '\n' +
    '- August 14, 2023: Launched the [large model data sharing training platform](https://llama.family), allowing everyone to contribute to large model training, even without computing resources. The data contributed by each community member will determine the future capabilities of the model!\n' +
    '\n' +
    '- August 3, 2023: Added GPU [inference acceleration](#-inference-acceleration) support for FasterTransformer and vLLM!\n' +
    '\n' +
    '- July 31, 2023: 【Major】The first truly meaningful Llama2 Chinese large model is released! Details can be found in the [community article](https://mp.weixin.qq.com/s/lExUU7z_MvgJ7tzQPF8tUQ)\n' +
    '\n' +
    '- July 28, 2023: Deployed a Q&A interface through [Docker](#docker-deployment-of-qa-interface)!\n' +
    '\n' +
    '- July 27, 2023: Added [LangChain](#langchain) support!\n' +
    '\n' +
    '- July 26, 2023: Released a [4-bit quantized compressed version](#-model-quantization) of the Llama2-13B Chinese fine-tuning parameters!\n' +
    '\n' +
    '- July 25, 2023: The community\'s WeChat public account "Llama Chinese Community" is now live. Feel free to follow for the latest updates and dynamics!\n' +
    '\n' +
    '- July 24, 2023: [FlagAlpha](https://huggingface.co/FlagAlpha) added Llama2-13B Chinese fine-tuned parameters!\n' +
    '\n' +
    '- July 24, 2023: [llama.family](https://llama.family/) added Llama2-70B online experience!\n' +
    '\n' +
    '- July 23, 2023: Released Llama2-13B Chinese fine-tuned parameters to the Hugging Face repository [FlagAlpha](https://huggingface.co/FlagAlpha)!\n' +
    '\n' +
    '- July 22, 2023: Llama2 online experience link [llama.family](https://llama.family/) is live, including both Meta original and Chinese fine-tuned versions!\n' +
    '\n' +
    '- July 21, 2023: Evaluated the Chinese Q&A capability of the Meta original Llama2 Chat model [Model Evaluation](#-model-evaluation)!\n' +
    '\n' +
    '- July 21, 2023: Added the Hugging Face version download link for Llama2 models in China!\n' +
    '\n' +
    '- July 20, 2023: Added [Feishu Knowledge Base Documentation](https://chinesellama.feishu.cn/wiki/space/7257824476874768388?ccm_open_type=lark_wiki_spaceLink), welcome everyone to contribute!\n' +
    '\n' +
    '- July 20, 2023: Chinese Llama2 latest download links are live!\n' +
    '\n' +
    '- July 19, 2023: Officially launched the Llama2 Chinese community, stay tuned for real-time updates!\n' +
    '\n' +
    '- July 19, 2023: Chinese Llama2 latest download links are in progress, stay tuned!\n' +
    '\n' +
    '- July 19, 2023: Launched the Llama2 Chinese community, welcome everyone to join!\n' +
    '\n' +
    '</details>\n' +
    '\n' +
    '\n' +
    '## 🐼 Latest Downloads of Llama2\n' +
    '\n' +
    'The code examples in this repository are primarily based on Hugging Face version parameters. We provide scripts to convert the model parameters released on the Meta website into the format supported by Hugging Face. You can directly load them using the transformers library: [Parameter Format Conversion](https://github.com/LlamaFamily/Llama-Chinese/blob/main/scripts/convert2hf/README.md)\n' +
    '\n' +
    '<details>\n' +
    '\n' +
    '- Llama2-7B Official Version: https://pan.xunlei.com/s/VN_kR2fwuJdG1F3CoF33rwpIA1?pwd=z9kf\n' +
    '\n' +
    '- Llama2-7B-Chat Official Version: https://pan.xunlei.com/s/VN_kQa1_HBvV-X9QVI6jV2kOA1?pwd=xmra\n' +
    '\n' +
    '- Llama2-13B Official Version: https://pan.xunlei.com/s/VN_izibaMDoptluWodzJw4cRA1?pwd=2qqb\n' +
    '\n' +
    '- Llama2-13B-Chat Official Version: https://pan.xunlei.com/s/VN_iyyponyapjIDLXJCNfqy7A1?pwd=t3xw\n' +
    '\n' +
    '- Llama2-7B Hugging Face Version: https://pan.xunlei.com/s/VN_t0dUikZqOwt-5DZWHuMvqA1?pwd=66ep\n' +
    '\n' +
    '- Llama2-7B-Chat Hugging Face Version: https://pan.xunlei.com/s/VN_oaV4BpKFgKLto4KgOhBcaA1?pwd=ufir\n' +
    '\n' +
    '- Llama2-13B Hugging Face Version: https://pan.xunlei.com/s/VN_yT_9G8xNOz0SDWQ7Mb_GZA1?pwd=yvgf\n' +
    '\n' +
    '- Llama2-13B-Chat Hugging Face Version: https://pan.xunlei.com/s/VN_yA-9G34NGL9B79b3OQZZGA1?pwd=xqrg\n' +
    '\n' +
    '- Llama2-70B-Chat Hugging Face Version: https://pan.xunlei.com/s/VNa_vCGzCy3h3N7oeFXs2W1hA1?pwd=uhxh#\n' +
    '\n' +
    '- CodeLlama-7B Official Version: https://pan.baidu.com/s/1cIPzdNywWLvQI7_2QanOEQ?pwd=zfwi\n' +
    '\n' +
    '- CodeLlama-7B-Python Official Version: https://pan.baidu.com/s/1liY8klGoDagYbpw-g-oFag?pwd=i952\n' +
    '\n' +
    '- CodeLlama-7B-Instruct Official Version: https://pan.baidu.com/s/108o9_DT2E_vfSGtOnDCQVw?pwd=zkt9\n' +
    '\n' +
    '- CodeLlama-13B Official Version: https://pan.baidu.com/s/1lLaeHv0XEBv0iiZzI1dpnw?pwd=qn99\n' +
    '\n' +
    '- CodeLlama-13B-Python Official Version: https://pan.baidu.com/s/1OLVfvZS_oqL3oqMKwsI87w?pwd=a78k\n' +
    '\n' +
    '- CodeLlama-13B-Instruct Official Version: https://pan.baidu.com/s/1HyxJl4w8wElgkZRh2ATrXQ?pwd=seg6\n' +
    '\n' +
    '- CodeLlama-34B Official Version: https://pan.baidu.com/s/1vEw0pFgIkctPUN4_5_6pIQ?pwd=q8eu\n' +
    '\n' +
    '</details>\n' +
    '\n' +
    '## 🔵 Atom Models\n' +
    '\n' +
    'The Atom models, created jointly by the Chinese Llama Community and AtomEcho, rank in the top ten of the Chinese Large Language Model Evaluation List C-Eval (submission on August 21).\n' +
    '\n' +
    '<p align="center" width="100%">\n' +
    '<img src="./assets/ceval.jpg" alt="ceval" style="width: 100%; display: block; margin: auto;">\n' +
    '</p>\n' +
    '\n' +
    'The Atom series includes Atom-1B, Atom-7B and Atom-13B, with continuous optimization of Chinese language proficiency based on Llama2. Atom-7B and Atom-7B-Chat are fully open source and available for commercial use. You can obtain the models on the [Hugging Face](https://huggingface.co/FlagAlpha) repository. Details are available in [Atom-7B Download](#atom-chinese-pretrained-model-based-on-llama2).\n' +
    '\n' +
    'Atom models have the following optimizations for Chinese:\n' +
    '\n' +
    '### Large-scale Chinese Data Pretraining\n' +
    '\n' +
    'Atom models are continually pretrained using a large amount of Chinese data, including encyclopedias, books, blogs, news, announcements, novels, financial data, legal data, medical data, code data, professional paper data, and Chinese natural language processing competition datasets. See [📝 Data Sources](#-data-sources) for details.\n' +
    '\n' +
    'The massive data is filtered, scored, and deduplicated, resulting in high-quality Chinese data exceeding 1T tokens, continuously added to the training iterations.\n' +
    '\n' +
    '### More Efficient Chinese Vocabulary\n' +
    '\n' +
    'To improve the efficiency of Chinese text processing, we optimized the vocabulary of the Llama2 model. First, based on several hundred gigabytes of Chinese text, we expanded the word library to 65,000 words on the basis of the model\'s vocabulary. Our improvements increased the Chinese encoding/decoding speed by about 350% according to tests. Additionally, we expanded the coverage of the Chinese character set, including all emoji symbols 😊. This makes generating articles with emoji symbols more efficient.\n' +
    '\n' +
    '### Adaptive Context Expansion\n' +
    '\n' +
    'Atom large models support a default context of 4K. Through position interpolation (PI) and Neural Tangent Kernel (NTK) methods, the context length can be expanded to 32K after fine-tuning.\n' +
    '\n' +
    '## 📝 Chinese Data\n' +
    '\n' +
    'We optimized the Chinese capabilities of Llama2 using the following data:\n' +
    '\n' +
    '| Type                                                       | Description                                                   |\n' +
    '| ---------------------------------------------------------- | ------------------------------------------------------------ |\n' +
    '| Web Data                                                   | Publicly available web data on the Internet, selecting deduplicated high-quality Chinese data involving encyclopedias, books, blogs, news, announcements, novels, etc. |\n' +
    '| [Wikipedia](https://github.com/goldsmith/Wikipedia)        | Chinese Wikipedia data                                        |\n' +
    '| [Wudao](https://github.com/BAAI-WuDao/Model)               | 200G of Chinese Wudao open-source data                         |\n' +
    '| [Clue](https://github.com/CLUEbenchmark/CLUEDatasetSearch) | High-quality Chinese long-text data cleaned from Clue\'s open Chinese pretraining data |\n' +
    '| Competition Datasets                                       | About 150 Chinese natural language processing multi-task competition datasets in recent years |\n' +
    '| [MNBVC](https://github.com/esbatmop/MNBVC)                 | Some datasets cleaned from MNBVC                              |\n' +
    '\n' +
    '**If you have high-quality datasets, we would greatly appreciate it if you could provide them to us! 💕💕**\n' +
    '\n' +
    '## ⏬ Model Deployment\n' +
    '\n' +
    'Meta provides download links for all models on 🤗Hugging Face: https://huggingface.co/meta-llama\n' +
    '\n' +
    'Download links for Chinese models from the Chinese Llama community: https://huggingface.co/FlagAlpha\n' +
    '\n' +
    '### Model Downloads\n' +
    '\n' +
    '#### Meta Official Llama2 Models\n' +
    '\n' +
    'The Llama2 pretrained models include 7B, 13B, and 70B versions. The Llama2-Chat model is fine-tuned based on the pretrained models and has enhanced conversational capabilities.\n' +
    '\n' +
    '|  Category  | Model Name   | 🤗Model Loading Name             | Download Link                                                 |\n' +
    '|  ----------  | ---------- | ------------------------- | --------------------- |\n' +
    '|  Pretrained  | Llama2-7B  | meta-llama/Llama-2-7b-hf  | [HuggingFace](https://huggingface.co/meta-llama/Llama-2-7b-hf) \\| [XunLei](https://pan.xunlei.com/s/VN_t0dUikZqOwt-5DZWHuMvqA1?pwd=66ep) |\n' +
    '|  Pretrained  | Llama2-13B | meta-llama/Llama-2-13b-hf | [HuggingFace](https://huggingface.co/meta-llama/Llama-2-13b-hf) \\| [XunLei](https://pan.xunlei.com/s/VN_yT_9G8xNOz0SDWQ7Mb_GZA1?pwd=yvgf) |\n' +
    '|  Pretrained  | Llama2-70B | meta-llama/Llama-2-70b-hf | [HuggingFace](https://huggingface.co/meta-llama/Llama-2-70b-hf) |\n' +
    '|  Chat  | Llama2-7B-Chat  | meta-llama/Llama-2-7b-chat-hf  | [HuggingFace](https://huggingface.co/meta-llama/Llama-2-7b-chat-hf) \\| [XunLei](https://pan.xunlei.com/s/VN_oaV4BpKFgKLto4KgOhBcaA1?pwd=ufir) |\n' +
    '|  Chat  | Llama2-13B-Chat | meta-llama/Llama-2-13b-chat-hf | [HuggingFace](https://huggingface.co/meta-llama/Llama-2-13b-chat-hf) \\| [XunLei](https://pan.xunlei.com/s/VN_yA-9G34NGL9B79b3OQZZGA1?pwd=xqrg) |\n' +
    '|  Chat  | Llama2-70B-Chat | meta-llama/Llama-2-70b-chat-hf | [HuggingFace](https://huggingface.co/meta-llama/Llama-2-70b-chat-hf) \\| [XunLei](https://pan.xunlei.com/s/VNa_vCGzCy3h3N7oeFXs2W1hA1?pwd=uhxh#) |\n' +
    '\n' +
    '\n' +
    '#### Fine-tuned Chinese Models Based on Llama2\n' +
    '\n' +
    'We fine-tuned the Llama2-Chat model based on a Chinese instruction dataset, enhancing its Chinese conversational abilities. LoRA parameters and merged parameters with the base model have been uploaded to [Hugging Face](https://huggingface.co/FlagAlpha) and currently include models for 7B and 13B.\n' +
    '\n' +
    '|  Category  | Model Name   | 🤗Model Loading Name             | Base Model Version |    Download Link                                                 |\n' +
    '|  ----------  | ---------- | ------------- |  ----------------- | ------------------- |\n' +
    '|  Merged Parameters | Llama2-Chinese-7b-Chat | FlagAlpha/Llama2-Chinese-7b-Chat  |    meta-llama/Llama-2-7b-chat-hf       |[HuggingFace](https://huggingface.co/FlagAlpha/Llama2-Chinese-7b-Chat)  |\n' +
    '|  Merged Parameters | Llama2-Chinese-13b-Chat | FlagAlpha/Llama2-Chinese-13b-Chat|     meta-llama/Llama-2-13b-chat-hf     |[HuggingFace](https://huggingface.co/FlagAlpha/Llama2-Chinese-13b-Chat) |\n' +
    '|  LoRA Parameters | Llama2-Chinese-7b-Chat-LoRA  | FlagAlpha/Llama2-Chinese-7b-Chat-LoRA  |     meta-llama/Llama-2-7b-chat-hf      |[HuggingFace](https://huggingface.co/FlagAlpha/Llama2-Chinese-7b-Chat-LoRA) |\n' +
    '|  LoRA Parameters | Llama2-Chinese-13b-Chat-LoRA | FlagAlpha/Llama2-Chinese-13b-Chat-LoRA |     meta-llama/Llama-2-13b-chat-hf     |[HuggingFace](https://huggingface.co/FlagAlpha/Llama2-Chinese-13b-Chat-LoRA) |\n' +
    '\n' +
    '\n' +
    '#### Pre-trained Chinese Model Atom based on Llama2\n' +
    '\n' +
    'The community provides pretrained versions Atom-7B and models fine-tuned for conversational purposes based on Atom-7B. Model parameters will be continuously updated. For more details on model progress, visit the community website [llama.family](https://llama.family).\n' +
    '\n' +
    '|  Category  | Model Name        | 🤗Model Loading Name                  | Download Link                                                 |\n' +
    '| --------------- | --------------- | ------------------------------ | ------------------------------------------------------------ |\n' +
    '|  Pretrained  | Atom-7B  | FlagAlpha/Atom-7B  | [HuggingFace](https://huggingface.co/FlagAlpha/Atom-7B) \\| [ModelScope](https://modelscope.cn/models/FlagAlpha/Atom-7B) \\| [WiseModel](https://wisemodel.cn/models/FlagAlpha/Atom-7B) |\n' +
    '|  Chat  | Atom-7B-Chat  | FlagAlpha/Atom-7B-Chat  | [HuggingFace](https://huggingface.co/FlagAlpha/Atom-7B-Chat) \\| [ModelScope](https://modelscope.cn/models/FlagAlpha/Atom-7B-Chat) \\| [WiseModel](https://wisemodel.cn/models/FlagAlpha/Atom-7B-Chat) |\n' +
    '\n' +
    '\n' +
    '### Code Examples\n' +
    '\n' +
    '```python\n' +
    'import torch\n' +
    'from transformers import AutoTokenizer, AutoModelForCausalLM\n' +
    'device_map = "cuda:0" if torch.cuda.is_available() else "auto"\n' +
    'model = AutoModelForCausalLM.from_pretrained(\'FlagAlpha/Atom-7B-Chat\', device_map=device_map, torch_dtype=torch.float16, load_in_8bit=True)\n' +
    'model = model.eval()\n' +
    'tokenizer = AutoTokenizer.from_pretrained(\'FlagAlpha/Atom-7B-Chat\', use_fast=False)\n' +
    'tokenizer.pad_token = tokenizer.eos_token\n' +
    'input_ids = tokenizer([\'<s>Human: Introduce China\\n</s><s>Assistant: \'], return_tensors="pt", add_special_tokens=False).input_ids\n' +
    'if torch.cuda.is_available():\n' +
    '  input_ids = input_ids.to(\'cuda\')\n' +
    'generate_input = {\n' +
    '    "input_ids": input_ids,\n' +
    '    "max_new_tokens": 512,\n' +
    '    "do_sample": True,\n' +
    '    "top_k": 50,\n' +
    '    "top_p": 0.95,\n' +
    '    "temperature": 0.3,\n' +
    '    "repetition_penalty": 1.3,\n' +
    '    "eos_token_id": tokenizer.eos_token_id,\n' +
    '    "bos_token_id": tokenizer.bos_token_id,\n' +
    '    "pad_token_id": tokenizer.pad_token_id\n' +
    '}\n' +
    'generate_ids  = model.generate(**generate_input)\n' +
    'text = tokenizer.decode(generate_ids[0])\n' +
    'print(text)\n' +
    '```\n' +
    '\n' +
    '### FastAPI Setup\n' +
    '\n' +
    'To facilitate model invocation via API, we provide a script for quickly building a [FastAPI](https://github.com/tiangolo/fastapi) interface. For related test code and API parameter settings, please refer to [API Call](https://github.com/LlamaFamily/Llama-Chinese/blob/main/scripts/api/README.md).\n' +
    '\n' +
    '### Gradio Setup\n' +
    '\n' +
    'Built on Gradio, the Q&A interface implements fluid output. Copy the following code into the console to run. The code below uses the Atom-7B model as an example, <font color="#006600">simply modify the model name in the code for different models 😊</font><br/>\n' +
    '\n' +
    '```\n' +
    'python examples/chat_gradio.py --model_name_or_path FlagAlpha/Atom-7B-Chat\n' +
    '```\n' +
    '\n' +
    '### Docker Setup\n' +
    'For details, refer to: [Docker Deployment](https://github.com/LlamaFamily/Llama-Chinese/blob/main/docs/chat_gradio_guide.md)\n' +
    '\n' +
    'Step 1: Prepare the Docker image and launch [chat_gradio.py](../examples/chat_gradio.py) through a Docker container.\n' +
    '```bash\n' +
    'git clone https://github.com/LlamaFamily/Llama-Chinese.git\n' +
    '\n' +
    'cd Llama-Chinese\n' +
    '\n' +
    'docker build -f docker/Dockerfile -t flagalpha/llama2-chinese:gradio .\n' +
    '```\n' +
    '\n' +
    'Step 2: Start chat_gradio through Docker-compose.\n' +
    '```bash\n' +
    'cd Llama-Chinese/docker\n' +
    'doker-compose up -d --build\n' +
    '```\n' +
    '\n' +
    '## 🚀 Inference Acceleration\n' +
    'As the parameter scale of large models continues to grow, improving model inference speed has become an important research direction with limited computational resources. Common inference acceleration frameworks include lmdeploy, FasterTransformer, vLLM, and JittorLLMs.\n' +
    '\n' +
    '### TensorRT-LLM\n' +
    '[TensorRT-LLM](https://github.com/NVIDIA/TensorRT-LLM/tree/main) is developed by NVIDIA, written in C++/CUDA, and supports distributed inference. \n' +
    '\n' +
    'For detailed inference documentation, visit: [inference-speed/GPU/TensorRT-LLM_example](https://github.com/LlamaFamily/Llama-Chinese/tree/main/inference-speed/GPU/TensorRT-LLM_example)\n' +
    '\n' +
    '### vLLM\n' +
    '[vLLM](https://github.com/vllm-project/vllm) is developed by the University of California, Berkeley, with its core technology being PageAttention. It achieves 24 times higher throughput compared to HuggingFace Transformers. Unlike FasterTransformer, vLLM is more user-friendly and does not require additional model conversion. It supports FP16 inference.\n' +
    '\n' +
    'For detailed inference documentation, visit: [inference-speed/GPU/vllm_example](https://github.com/LlamaFamily/Llama-Chinese/blob/main/inference-speed/GPU/vllm_example/README.md)\n' +
    '\n' +
    '### JittorLLMs\n' +
    '[JittorLLMs](https://github.com/Jittor/JittorLLMs) is led by Non-ten Technology in collaboration with the Visual Media Research Center at Tsinghua University. It significantly reduces hardware requirements by 80% through a dynamic swap mechanism. Jittor framework, with zero-copy technology, reduces the loading overhead of large models by 40% compared to PyTorch. Moreover, automatic compilation optimization through meta-operators enhances computational performance by over 20%.\n' +
    '\n' +
    'For detailed inference documentation, visit: [inference-speed/GPU/JittorLLMs](https://github.com/LlamaFamily/Llama-Chinese/blob/main/inference-speed/GPU/JittorLLMs_example/README.md)\n' +
    '\n' +
    '### lmdeploy\n' +
    '[lmdeploy](https://github.com/InternLM/lmdeploy/) is developed by the Shanghai AI Lab, using C++/CUDA for inference. It provides Python/gRPC/HTTP interfaces and a WebUI for inference, supporting tensor parallel distributed inference and FP16/weight int4/kv cache int8 quantization.\n' +
    '\n' +
    'For detailed inference documentation, visit: [inference-speed/GPU/lmdeploy_example](https://github.com/LlamaFamily/Llama-Chinese/tree/main/inference-speed/GPU/lmdeploy_example)\n' +
    '\n' +
    '\n' +
    '\n' +
    '## 🥇 Model Evaluation\n' +
    'To gain a clearer understanding of the Chinese question-answering capabilities of the Llama2 model, we selected a set of representative Chinese questions for testing. The tested models include Meta\'s publicly available versions, namely, Llama2-7B-Chat and Llama2-13B-Chat, without any fine-tuning or training. The test questions were curated from [AtomBulb](https://github.com/AtomEcho/AtomBulb), totaling 95 questions covering eight major categories: general knowledge, language understanding, creative ability, logical reasoning, code programming, work skills, tool usage, and personality traits.\n' +
    '\n' +
    'The prompt used during testing is as follows, for example, for the question "List 5 methods to improve sleep quality":\n' +
    '\n' +
    '```plaintext\n' +
    '[INST] \n' +
    '<<SYS>>\n' +
    'You are a helpful, respectful and honest assistant. Always answer as helpfully as possible, while being safe.  Your answers should not include any harmful, unethical, racist, sexist, toxic, dangerous, or illegal content. Please ensure that your responses are socially unbiased and positive in nature. The answer always been translate into Chinese language.\n' +
    '\n' +
    'If a question does not make any sense, or is not factually coherent, explain why instead of answering something not correct. If you don\'t know the answer to a question, please don\'t share false information.\n' +
    '\n' +
    'The answer always been translated into Chinese language.\n' +
    '<</SYS>>\n' +
    '\n' +
    'List 5 methods to improve sleep quality\n' +
    '[/INST]\n' +
    '```\n' +
    'The test results for Llama2-7B-Chat can be found at[meta_eval_7B.md](assets/meta_eval_7B.md)，and for Llama2-13B-Chat at [meta_eval_13B.md](assets/meta_eval_13B.md)。\n' +
    '\n' +
    'Through our testing, we observed that Meta\'s original Llama2 Chat model generally has mediocre alignment with Chinese questions. In most cases, it fails to provide Chinese answers, or the responses are a mixture of Chinese and English. Therefore, it is crucial to train and fine-tune the Llama2 model on Chinese data. Our Chinese version of the Llama2 model is currently undergoing training and will be made available to the community in the near future.\n' +
    '\n' +
    '\n' +
    '## 💪 Extension Capabilities\n' +
    '\n' +
    'In addition to continually enhancing the intrinsic qualities of large models, such as knowledge base, general understanding, logical reasoning, and imaginative capabilities, we are also actively expanding the extension capabilities of the large models. This includes features like knowledge base retrieval, computational tools, WolframAlpha integration, and software manipulation.\n' +
    '\n' +
    'We have initially integrated the LangChain framework to facilitate the development of applications like document retrieval, question-answering bots, and intelligent agents based on the Llama2 model. For more information on LangChain, please refer to [LangChain](https://github.com/langchain-ai/langchain).\n' +
    '\n' +
    '### LangChain\n' +
    'For a simplified implementation using the LangChain framework with the Llama2 LLM class, refer to [examples/llama2_for_langchain.py](https://github.com/LlamaFamily/Llama-Chinese/blob/main/examples/llama2_for_langchain.py). Here is a basic code snippet:\n' +
    '\n' +
    '```python\n' +
    'from llama2_for_langchain import Llama2\n' +
    '\n' +
    '# Example using FlagAlpha/Atom-7B-Chat\n' +
    'llm = Llama2(model_name_or_path=\'FlagAlpha/Atom-7B-Chat\')\n' +
    '\n' +
    'while True:\n' +
    '    human_input = input("Human: ")\n' +
    '    response = llm(human_input)\n' +
    '    print(f"Llama2: {response}")\n' +
    '```\n' +
    '\n' +
    '## 🐞 Code Model\n' +
    'Meta officially released Code Llama on August 24, 2023, which is a fine-tuned version of Llama2 based on code data. It provides three versions with different functionalities: Base Model (Code Llama), Python-specific Model (Code Llama - Python), and Instruction-following Model (Code Llama - Instruct), each available in 7B, 13B, and 34B parameter sizes. The capabilities of different models are summarized in the following table:\n' +
    '\n' +
    '|  Model Category         |        Model Name         | Code Completion | Code Fill | Instruction Programming |\n' +
    '|-----------------------|------------------------|------|------|------|\n' +
    '| Code Llama            | CodeLlama-7b           | ✅    | ✅    | ❌    |\n' +
    '|                       | CodeLlama-13b          | ✅    | ✅    | ❌    |\n' +
    '|                       | CodeLlama-34b          | ✅    | ❌    | ❌    |\n' +
    '| Code Llama - Python   | CodeLlama-7b-Python    | ✅    | ❌    | ❌    |\n' +
    '|                       | CodeLlama-13b-Python   | ✅    | ❌    | ❌    |\n' +
    '|                       | CodeLlama-34b-Python   | ✅    | ❌    | ❌    |\n' +
    '| Code Llama - Instruct | CodeLlama-7b-Instruct  | ❌    | ✅    | ✅    |\n' +
    '|                       | CodeLlama-13b-Instruct | ❌    | ✅    | ✅    |\n' +
    '|                       | CodeLlama-34b-Instruct | ❌    | ❌    | ✅    |\n' +
    '\n'

postList = [
  {
    title: 'Title1',
    author: 'Author1',
    time: '2024-4-4',
  },
  {
    title: 'Title2',
    author: 'Author2',
    time: '2024-4-4',
  },
  {
    title: 'Title3',
    author: 'Author3',
    time: '2024-4-4',
  },
  {
    title: 'Title4',
    author: 'Author4',
    time: '2024-4-4',
  },
  {
    title: 'Title5',
    author: 'Author5',
    time: '2024-4-4',
  },
]
</script>

<template>
  <div>
    <p>Header</p>
  </div>
  <div class="main">
    <div class="left-body">
      <div>
        <h1 class="event-title">{{ title }}</h1>
      </div>

      <div class="name-time-wrap">
        <p style="margin-top: 5px; margin-bottom: 5px">{{ eventName }}</p>
      </div>

      <div style="margin-top: 10px; margin-left: 5px">
        报名时间: {{applyStartTime}} - {{applyEndTime}}
      </div>

      <div style="margin-top: 10px; margin-left: 5px">
        活动时间: {{applyStartTime}} - {{applyEndTime}}
      </div>

      <div>
        <p style="margin-top: 10px"
        >{{ stars }}</p>
      </div>

      <div>
        <img :src="posterUrl"/>
      </div>

      <div>
        <v-md-preview :text="test_text"></v-md-preview>
      </div>



      <comment comment-block-id="1"></comment>


    </div>

    <div class="right-panel">
      <div class="author-wrap">
        <Avatar :user-id="authorId" :need-small="true" size-small="60px" name="LampTales"></Avatar>
        <p style="margin-left: 40px; font-size: 18px;"
        >{{ authorName }}</p>
      </div>
      <div>
        <p class="event-title">Related Posts</p>
      </div>

      <div>
        <el-card v-for="post in postList" :key="post.title" style="margin-top: 20px">
          <el-row>
            <el-col>
              <p>{{ post.title }}</p>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <p>{{ post.author }}</p>
            </el-col>
            <el-col>
              <p>{{ post.time }}</p>
            </el-col>
          </el-row>
        </el-card>
      </div>
    </div>

  </div>

  <div class="bottom-button">
    <el-button type="primary" @click="handleClick">我要参加</el-button>
    <el-button type="primary"
               @click="handleClick"
               style="margin-left: 20px; margin-right: 50px"
    >我想发帖</el-button>
  </div>
</template>

<style scoped>

.main {
  width: 99vw;
  display: flex;
  flex-direction: row;
  overflow-y: scroll;
  height: 85vh;
}

.event-title {
  font-size: 20px;
}

.name-time-wrap {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin-top: 5px;
  margin-bottom: 5px;
  margin-left: 5px;
}

.left-body {
  width: 70%;
  margin-left: 20px;
}

.right-panel {
  margin-left: 20px;
  width: 25%;
}

.author-wrap {
  display: flex;
  flex-direction: row;
  align-items: center;
  height: auto;
  margin-bottom: 60px;
}

.bottom-button {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  height: 7vh;
  margin-right: 100px;
  margin-left: 50px;
}
</style>