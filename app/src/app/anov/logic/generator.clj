(ns app.anov.logic.generator
  (:require [app.utils :refer :all]))

(declare gen-section-prompts gen-content-prompts)

(defn generate-sections
  "Generate sections of an article"
  [openai {:keys [n-sections title prompt] :as section-data}]
  (let [gen-fn (:gen-fn openai)
        result (gen-fn {:model    :gpt-3
                        :messages (gen-section-prompts
                                    {:n-sections n-sections
                                     :prompt     prompt
                                     :title      title})})]
    (pres result)
    result))

(defn generate-content
  "Generate content of a section"
  [openai article-data {:keys [title description] :as section-data}]
  (let [gen-fn (:gen-fn openai)
        result (gen-fn {:model    :gpt-3
                        :messages (gen-content-prompts
                                    {:title       title
                                     :description description
                                     :article-title (:title article-data)
                                     :article-prompt (:prompt article-data)})})]
    (pres result)
    result))

(defn gen-content-prompts
  "Generate prompts for content"
  [{:keys [title description article-title article-prompt]}]
  [{:role    "system"
    :content "I'm a very good writer that can help you write enlightening articles that help people unlearn their wrong beliefs and learn new insights to see the world according to scientific knowledge and use good logical thinking"}
   {:role    "user"
    :content (str "Ok, gue lagi nulis artikel panjang tentang ini " article-title ". Isinya the whole article kira2x begini " article-prompt)}
   {:role    "assistant"
    :content "Ok, gue bantu di bagian mana?"}
   {:role    "user"
    :content (str "Gue mau elo tulis penjelasan di bagian ini " title ". Isinya section yg elo tulis itu begini " description)}
   {:role    "assistant"
    :content "Ok, bahasanya gimana?"}
   {:role    "user"
    :content "Gue mau bahasanya in english yg informal, santai, witty, smart, and humorous tapi jangan cringe"}
   {:role    "assistant"
    :content "Ok, formatnya gimana?"}
   {:role    "user"
    :content "Formatnya json, isinya :section-title & :section-content, di section-content elo tulis penjelasan elo dengan format html, kalo elo kasih
     contoh coding elo kasih format code block di html-nya yak"}])

(defn gen-section-prompts
  "Generate prompts for sections"
  [{:keys [title n-sections prompt]}]
  [{:role    "system"
    :content "I'm a very good writer that can help you write enlightening articles that help people unlearn their wrong beliefs and learn new insights to see the world according to scientific knowledge and use good logical thinking"}
   {:role    "user"
    :content (str "Ok, gue mau nulis article panjang yg dipecah jadi beberapa section tentang ini " title)}
   {:role    "assistant"
    :content "Ok, gue bantu di bagian mana?"}
   {:role    "user"
    :content (str "Gue mau elo pecah penjelasannya dalam " n-sections " section. Tiap section isinya ada :title & :description.
                   Gue mau elo kasih beberapa ide cara mecah article ini beberapa sections, tiap section isinya ada :title & :description.")}
   {:role    "assistant"
    :content "Ok, detailnya elo pengen gue jelasin bagian apa?"}
   {:role    "user"
    :content (str "Gue mau elo jelasin ini " prompt)}
   {:role    "assistant"
    :content "Ok, bahasanya gimana?"}
   {:role    "user"
    :content "Gue mau bahasanya in english yg informal, santai, witty, smart, and humorous tapi jangan cringe"}
   {:role    "assistant"
    :content "Ok, formatnya gimana?"}
   {:role    "user"
    :content "Formatnya json, list of sections, tiap section ada :title & :description"}])


