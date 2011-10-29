// Autogenerated Jamon proxy
// C:/workspace_java/jamon-test/src/jamontest/JamonTestTemplate.jamon


@org.jamon.annotations.Template(
  signature = "40D3AB0ED7F3BB2C9F796FDB3DF9B46E",
  requiredArguments = {
    @org.jamon.annotations.Argument(name = "date", type = "java.util.Date"),
    @org.jamon.annotations.Argument(name = "s", type = "String[]")})
public class JamonTestTemplate
  extends org.jamon.AbstractTemplateProxy
{
  
  public JamonTestTemplate(org.jamon.TemplateManager p_manager)
  {
     super(p_manager);
  }
  
  protected JamonTestTemplate(String p_path)
  {
    super(p_path);
  }
  
  public JamonTestTemplate()
  {
     super("/JamonTestTemplate");
  }
  
  public interface Intf
    extends org.jamon.AbstractTemplateProxy.Intf
  {
    
    void renderNoFlush(final java.io.Writer jamonWriter) throws java.io.IOException;
    
  }
  public static class ImplData
    extends org.jamon.AbstractTemplateProxy.ImplData
  {
    // 2, 3
    public void setDate(java.util.Date date)
    {
      // 2, 3
      m_date = date;
    }
    public java.util.Date getDate()
    {
      return m_date;
    }
    private java.util.Date m_date;
    // 3, 3
    public void setS(String[] s)
    {
      // 3, 3
      m_s = s;
    }
    public String[] getS()
    {
      return m_s;
    }
    private String[] m_s;
  }
  @Override
  protected org.jamon.AbstractTemplateProxy.ImplData makeImplData()
  {
    return new ImplData();
  }
  @Override public ImplData getImplData()
  {
    return (ImplData) super.getImplData();
  }
  
  
  @Override
  public org.jamon.AbstractTemplateImpl constructImpl(Class<? extends org.jamon.AbstractTemplateImpl> p_class){
    try
    {
      return p_class
        .getConstructor(new Class [] { org.jamon.TemplateManager.class, ImplData.class })
        .newInstance(new Object [] { getTemplateManager(), getImplData()});
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  protected org.jamon.AbstractTemplateImpl constructImpl(){
    return new JamonTestTemplateImpl(getTemplateManager(), getImplData());
  }
  public org.jamon.Renderer makeRenderer(final java.util.Date date, final String[] s)
  {
    return new org.jamon.AbstractRenderer() {
      @Override
      public void renderTo(final java.io.Writer jamonWriter)
        throws java.io.IOException
      {
        render(jamonWriter, date, s);
      }
    };
  }
  
  public void render(final java.io.Writer jamonWriter, final java.util.Date date, final String[] s)
    throws java.io.IOException
  {
    renderNoFlush(jamonWriter, date, s);
    jamonWriter.flush();
  }
  public void renderNoFlush(final java.io.Writer jamonWriter, final java.util.Date date, final String[] s)
    throws java.io.IOException
  {
    ImplData implData = getImplData();
    implData.setDate(date);
    implData.setS(s);
    Intf instance = (Intf) getTemplateManager().constructImpl(this);
    instance.renderNoFlush(jamonWriter);
    reset();
  }
  
  
}
